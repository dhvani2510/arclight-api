package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Embeddable
public class Profile implements UserDetails {
    public Long Id;
    
    @Column(updatable = true)
    public String firstName;
    
    @Column(updatable = true)
    public String lastName;
    
    @Column(updatable = false)
    public LocalDate birthDay;
    
    @Column(unique = true, updatable = false)
    public String email;
    
    public String Password; // Hashed
    
    @OneToOne(cascade = CascadeType.ALL)
    public File image;
    
    @Enumerated(EnumType.STRING)
    public Language language;

    @Transient
    public Integer Age;
    
    @Enumerated(EnumType.STRING)
    private Role Role;

    public Profile(String name, String surname, String email, LocalDate birthDay, File image, Language language) {
        this.firstName = name;
        this.lastName = surname;
        this.email = email;
        this.birthDay = birthDay;
        this.language = language;
        this.Age = getAge();
        this.image = image;
        this.Role = com.example.arclight.entities.datatypes.Role.User;
    }

    public Profile() {
    }

    public Integer getAge() {
        return Period.between(this.birthDay, LocalDate.now()).getYears();
    }

    public void setRole(Role role) {
        Role = role;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return Password;
    }
    
    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
    
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Builder design pattern
    private Profile(ProfileBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.Id = builder.Id;
        this.email = builder.Email;
        this.Password = builder.Password;
        this.Role = builder.Role;
    }

    public void setPassword(String password) {
        Password = password; // encrypt
    }

    // Builder Class
    public static class ProfileBuilder {
        // required parameters
        private String firstName;
        private String lastName;

        // optional parameters
        private Long Id;
        private String Email;
        private String Password;
        private Role Role;
        private File image;
        private Language language;

        public ProfileBuilder(String name, String surname) {
            this.firstName = name;
            this.lastName = surname;
        }

        public ProfileBuilder setId(Long id) {
            this.Id = id;
            return this;
        }

        public ProfileBuilder setEmail(String email) {
            this.Email = email;
            return this;
        }

        public ProfileBuilder setPassword(String password) {
            this.Password = password;
            return this;
        }

        public ProfileBuilder setRole(Role role) {
            this.Role = role;
            return this;
        }

        public ProfileBuilder setImage(File image) {
            this.image = image;
            return this;
        }
        
        public ProfileBuilder setLanguage(Language language) {
            this.language = language;
            return this;
        }
        
        public Profile build() {
            return new Profile(this);
        }
    }
}
