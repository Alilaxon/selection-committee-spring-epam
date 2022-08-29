package com.epam.selectionСommitteeSpring.model.entity;

import com.epam.selectionСommitteeSpring.model.dto.FacultyForm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(name = "name")
   private String name;

    @Column(name = "budget_places")
   private Integer budgetPlaces;

    @Column(name = "general_places")
   private Integer generalPlaces;


    @ManyToMany
    @JoinTable( name = "faculties_subjects",
    joinColumns = @JoinColumn(name ="faculty_id"),
            inverseJoinColumns =@JoinColumn( name = "subject_id"))
    private List<Subject> subjects;

    @Column(name = "recruitment")
    Boolean recruitment;
    public Faculty() {
    }

    public Faculty(Long id,
                   String name,
                   Integer budgetPlaces,
                   Integer generalPlaces,
                   List<Subject> subjects,
                   Boolean recruitment) {
        this.id = id;
        this.name = name;
        this.budgetPlaces = budgetPlaces;
        this.generalPlaces = generalPlaces;
        this.subjects = subjects;
        this.recruitment = recruitment;
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

    public Boolean getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Boolean recruitment) {
        this.recruitment = recruitment;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
