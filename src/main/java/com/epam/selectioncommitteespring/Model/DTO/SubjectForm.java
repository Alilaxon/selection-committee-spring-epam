package com.epam.selectioncommitteespring.Model.DTO;


import javax.validation.constraints.NotBlank;

public class SubjectForm {

    @NotBlank
    private String nameEN;

    @NotBlank
    private String nameRU;

    @NotBlank
    private String nameUK;
}
