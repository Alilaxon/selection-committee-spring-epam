package com.epam.selectioncommitteespring.Model.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;




  //  @Column(name = "firstname")
    private String firstname;
  //  @Column(name = "surname")
    private String surname;
  //  @Column(name = "city")
    private String city;
  //  @Column(name = "region")
    private String region;

  //  @Column(name = "institution")
    private String  institution;



    public User() {
    }

    public User(String login,
                String password,
                String email,
                String firstname,
                String surname,
                String city,
                String region) {

        this.username = login;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
