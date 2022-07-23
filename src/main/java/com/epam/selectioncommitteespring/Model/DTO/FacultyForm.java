package com.epam.selectioncommitteespring.Model.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FacultyForm {




    @NotBlank(message = "field can not be empty")
    private String facultyName;

    @NotNull(message = "field can not be empty")
    @Min(value = 0,message = "min value is 0")
    private Integer budgetPlaces;

    @NotNull(message = "field can not be empty")
    @Min(value = 1,message = "min value is 1")
    private Integer generalPlaces;

    public FacultyForm() {
    }

    public FacultyForm(String facultyName, Integer budgetPlaces, Integer generalPlaces) {
        this.facultyName = facultyName;
        this.budgetPlaces = budgetPlaces;
        this.generalPlaces = generalPlaces;
    }



    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Integer getBudgetPlaces() {
        return budgetPlaces;
    }

    public void setBudgetPlaces(Integer budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
    }

    public Integer getGeneralPlaces() {
        return generalPlaces;
    }

    public void setGeneralPlaces(Integer generalPlaces) {
        this.generalPlaces = generalPlaces;
    }
}
