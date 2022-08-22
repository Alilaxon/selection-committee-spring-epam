package com.epam.selectionСommitteeSpring.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserForm {
    @NotBlank(message = "username should not be empty")
    @Size(min = 2, max = 32, message = "login size must be 2-32")
    private String username

            ;
    @NotBlank
    @Size(min = 1, max = 32, message = "password size must be 8-32")
    private String password;
    @NotBlank
    @Size(min=2,max = 32)
    private String email;
  //  @NotBlank
  //  @Size(min=2,max = 32)
    private String firstname;
  //  @NotBlank
  //  @Size(min=2,max = 32)
    private String surname;
 //   @NotBlank
 //   @Size(min=2,max = 32)
    private String city;
  //  @NotBlank
 //   @Size(min=2,max = 32)
    private String region;

 //   @NotBlank
 //   @Size(min=2,max = 32)
    private String  institution;

    public UserForm() {
    }

    public UserForm(String username,
                    String password,
                    String email,
                    String firstname,
                    String surname,
                    String city,
                    String region,
                    String institution) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
        this.region = region;
        this.institution = institution;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
