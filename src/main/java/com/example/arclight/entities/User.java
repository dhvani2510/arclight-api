package com.example.arclight.entities;

import com.example.arclight.entities.datatypes.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class User extends  BaseEntity implements UserDetails
{ //Student
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    public  String firstName;
    public  String lastName;
    public LocalDate birthDay;
    @Column(unique = true)
    public  String email;

    public  String Password; // Hashed

    @OneToOne(cascade = CascadeType.PERSIST)
    public  File Image;

    @OneToOne(cascade = CascadeType.PERSIST)
    public  Language SecondaryLanguage;
    @Transient
    public Integer Age;

    @Enumerated(EnumType.STRING)
     private  Role Role;

//    @ManyToMany(mappedBy = "UserLanguages")
//    Set<Language> Languages;

    public  User(String name, String surname, String email, LocalDate birthDay){

        this.firstName= name;
        this.lastName= surname;
        this.email=email;
        this.birthDay= birthDay;
        this.Age=getAge();
        this.CreatedAt= LocalDateTime.now();
        this.Role= com.example.arclight.entities.datatypes.Role.User;
    }

    public User() {
    }

    public Integer getAge(){
        return Period.between(this.birthDay,LocalDate.now()).getYears();
    }
    public void setRole(Role role) {
        Role=role;
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
        return email; //email
    }

    @Override
    public String getPassword() {
        return Password;
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
    private User(UserBuilder builder) {
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.Id=builder.Id;
        this.email=builder.Email;
        this.Password= builder.Password;
        this.Role=builder.Role;
    }

    public void setPassword(String password) {
        Password = password; // encrypt
    }


    //Builder Class
    public static class UserBuilder{

        // required parameters
        private String firstName;
        private String lastName;

        // optional parameters
        private Long Id;

        private String Email;
        private String Password;
        private  Role Role;

        public UserBuilder(String name, String surname){
            this.firstName=name;
            this.lastName=surname;
        }

        public UserBuilder setId(Long id) {
            this.Id = id;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.Email = email;
            return this;
        }
        public UserBuilder setPassword(String password) {
            this.Password = password;
            return this;
        }
        public UserBuilder setRole(Role role) {
            this.Role = role;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }
}
