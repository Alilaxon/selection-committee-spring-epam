package com.epam.selectioncommittee.spring.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "surname")
    private String surname;
    @Column(name = "city")
    private String city;
    @Column(name = "region")
    private String region;
    @Column(name = "institution")
    private String institution;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "blocked")
    private Boolean blocked;


    public User() {
    }

    public User(Long id, String username, String password,
                String email, String firstname, String surname,
                String city, String region, String institution,
                Role role, Boolean blocked) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
        this.region = region;
        this.institution = institution;
        this.role = role;
        this.blocked = blocked;

    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName().name()));
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

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", institution='" + institution + '\'' +
                ", role=" + role +
                ", blocked=" + blocked +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id)
//                && username.equals(user.username)
//                && password.equals(user.password)
//                && email.equals(user.email)
//                && firstname.equals(user.firstname)
//                && surname.equals(user.surname)
//                && city.equals(user.city)
//                && region.equals(user.region)
//                && institution.equals(user.institution)
//                && role.equals(user.role)
//                && blocked.equals(user.blocked);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, username, password, email,
//                firstname, surname, city, region,
//                institution, role, blocked);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && username.equals(user.username) && password.equals(user.password) && email.equals(user.email) && firstname.equals(user.firstname) && surname.equals(user.surname) && city.equals(user.city) && region.equals(user.region) && institution.equals(user.institution) && role.equals(user.role) && blocked.equals(user.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, firstname, surname, city, region, institution, role, blocked);
    }
}
