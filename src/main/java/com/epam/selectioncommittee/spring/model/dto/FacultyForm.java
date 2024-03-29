package com.epam.selectioncommittee.spring.model.dto;

import com.epam.selectioncommittee.spring.model.entity.Subject;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FacultyForm {


    private Long id;

    @NotBlank(message = "field can not be empty")
    private String facultyName;
    @NotBlank
    private String facultyNameRU;

    @NotNull(message = "field can not be empty")
    @Min(value = 0,message = "min value is 0")
    private Integer budgetPlaces;

    @NotNull(message = "field can not be empty")
    @Min(value = 1,message = "min value is 1")
    private Integer generalPlaces;

    private Boolean recruitment;

    private List<Subject> requiredSubjects;

    public FacultyForm() {
    }

    public FacultyForm(Long id,
                       String facultyName,
                       String facultyNameRU,
                       Integer budgetPlaces,
                       Integer generalPlaces,
                       List<Subject> requiredSubjects,
                       Boolean recruitment) {
        this.id = id;
        this.facultyName = facultyName;
        this.facultyNameRU = facultyNameRU;
        this.budgetPlaces = budgetPlaces;
        this.generalPlaces = generalPlaces;
        this.requiredSubjects = requiredSubjects;
        this.recruitment = recruitment;
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

    public List<Subject> getRequiredSubjects() {

        return requiredSubjects;
    }

    public void setRequiredSubjects(List<Subject> requiredSubjects) {

        this.requiredSubjects = requiredSubjects;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Boolean getRecruitment() {

        return recruitment;
    }

    public void setRecruitment(Boolean recruitment) {

        this.recruitment = recruitment;
    }

    public String getFacultyNameRU() {
        return facultyNameRU;
    }

    public void setFacultyNameRU(String facultyNameRU) {

        this.facultyNameRU = facultyNameRU;
    }

    @Override
    public String toString() {
        return "FacultyForm{" +
                "id=" + id +
                ", facultyName='" + facultyName + '\'' +
                ", recruitment=" + recruitment +
                '}';
    }
}
