package com.epam.selectionСommitteeSpring.model.DTO;


import javax.validation.constraints.NotBlank;

public class SubjectForm {

    @NotBlank(message = "field can not be empty")
    private String nameEN;

    @NotBlank(message = "field can not be empty")
    private String nameRU;


    public SubjectForm() {
    }

    public SubjectForm(String nameEN,
                       String nameRU
                       ) {
        this.nameEN = nameEN;
        this.nameRU = nameRU;

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


}
