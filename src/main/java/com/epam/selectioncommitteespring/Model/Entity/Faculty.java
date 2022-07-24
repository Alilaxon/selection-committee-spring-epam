package com.epam.selectioncommitteespring.Model.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "budget_places")
    Integer budgetPlaces;

    @Column(name = "general_places")
    Integer generalPlaces;


    @ManyToMany
    @JoinTable( name = "faculties_subjects",
    joinColumns = @JoinColumn(name ="faculty_id"),
            inverseJoinColumns =@JoinColumn( name = "subject_id"))
    private List<Subject> subjects;

    public Faculty() {
    }

    public Faculty(String name,
                   Integer budgetPlaces,
                   Integer generalPlaces) {

        this.name = name;
        this.budgetPlaces = budgetPlaces;
        this.generalPlaces = generalPlaces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
