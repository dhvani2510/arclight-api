package com.example.arclight.entities;
import com.example.arclight.entities.datatypes.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
public class User extends  BaseEntity implements UserDetails
{ //Student
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long Id;
    public  String Name;
    public  String Surname;
    public LocalDate BirthDay;
    public  String Email;

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

        this.Name= name;
        this.Surname= surname;
        this.Email=email;
        this.BirthDay= birthDay;
        this.Age=getAge();
        this.CreatedAt= LocalDateTime.now();
    }
//    public  User(String name, String surname, String email, LocalDate birthDay){
//
//        this.Name= name;
//        this.Surname= surname;
//        this.Email=email;
//        this.BirthDay= birthDay;
//        this.Age=getAge();
//    }

    public User() {
    }

    public Integer getAge(){
        return Period.between(this.BirthDay,LocalDate.now()).getYears();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.name()));
    }

    @Override
    public String getUsername() {
        return Name + "." + Surname; //email
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
}
