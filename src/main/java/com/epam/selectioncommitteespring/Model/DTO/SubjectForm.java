package com.epam.selectioncommitteespring.Model.DTO;


import javax.validation.constraints.NotBlank;

public class SubjectForm {

    @NotBlank(message = "field can not be empty")
    private String nameEN;

    @NotBlank(message = "field can not be empty")
    private String nameRU;

    @NotBlank(message = "field can not be empty")
    private String nameUK;

    public SubjectForm() {
    }

    public SubjectForm(String nameEN,
                       String nameRU,
                       String nameUK) {
        this.nameEN = nameEN;
        this.nameRU = nameRU;
        this.nameUK = nameUK;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {

        this.nameEN = nameEN;
    }

    public String getNameRU() {

        return nameRU;
    }

    public void setNameRU(String nameRU) {

        this.nameRU = nameRU;
    }

    public String getNameUK() {

        return nameUK;
    }

    public void setNameUK(String nameUK) {

        this.nameUK = nameUK;
    }
}
