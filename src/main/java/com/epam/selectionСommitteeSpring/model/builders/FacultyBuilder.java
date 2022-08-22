package com.epam.selectionСommitteeSpring.model.builders;

import com.epam.selectionСommitteeSpring.model.dto.FacultyForm;
import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.entity.Subject;

import java.util.List;

public class FacultyBuilder {
    private Long id;

    private String facultyName;

    private Integer budgetPlaces;

    private Integer generalPlaces;

    private Boolean recruitment;

    private List<Subject> requiredSubjects;

    public static FacultyBuilder builder(){
        return new FacultyBuilder();
    }

    public FacultyBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FacultyBuilder facultyName(String facultyName) {
        this.facultyName = facultyName;
        return this;
    }

    public FacultyBuilder budgetPlaces(Integer budgetPlaces) {
        this.budgetPlaces = budgetPlaces;
        return this;
    }

    public FacultyBuilder generalPlaces(Integer generalPlaces) {
        this.generalPlaces = generalPlaces;
        return this;
    }

    public FacultyBuilder recruitment(Boolean recruitment) {
        this.recruitment = recruitment;
        return this;
    }

    public FacultyBuilder requiredSubjects(List<Subject> requiredSubjects) {
        this.requiredSubjects = requiredSubjects;
        return this;
    }

    public Faculty build(){
        return new Faculty(id,facultyName,budgetPlaces,generalPlaces,requiredSubjects,recruitment);
    }

}
