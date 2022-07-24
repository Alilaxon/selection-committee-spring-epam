package com.epam.selectioncommitteespring.Model.Entity;


import javax.persistence.*;


@Entity
@Table(name = "faculties_subjects")
public class RequiredSubjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty facultyId;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subjectId;

}
