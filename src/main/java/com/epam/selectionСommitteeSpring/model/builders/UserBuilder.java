package com.epam.selectionСommitteeSpring.model.builders;

import com.epam.selectionСommitteeSpring.model.entity.Role;
import com.epam.selectionСommitteeSpring.model.entity.User;

public class UserBuilder  {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstname;

    private String surname;

    private String city;

    private String region;

    private String  institution;

    private Role role;

    private Boolean blocked;

    public static UserBuilder builder(){

        return new UserBuilder();
    }


    public UserBuilder id(Long id){
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder city(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder institution(String institution) {
        this.institution = institution;
        return this;
    }

    public UserBuilder region(String region) {
        this.region = region;
        return this;


    }

    public UserBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder blocked(Boolean blocked) {
        this.blocked = blocked;
        return this;
    }
   public User build(){
        return new User(id,username,password,email
                ,firstname,surname,city, region,institution
                ,role,blocked);
   }
}
