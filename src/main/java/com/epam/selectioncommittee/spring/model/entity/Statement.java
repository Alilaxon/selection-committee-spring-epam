package com.epam.selectioncommittee.spring.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "statements")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty facultyId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "gpa")
    private Long gradePointAverage;
    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position_id;

    public Statement() {
    }

    public Statement(Long id,
                     Faculty facultyId,
                     User userId,
                     Long gradePointAverage,
                     Position position_id) {
        this.id = id;
        this.facultyId = facultyId;
        this.userId = userId;
        this.gradePointAverage = gradePointAverage;
        this.position_id = position_id;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Faculty getFacultyId() {

        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {

        this.facultyId = facultyId;
    }

    public User getUserId() {

        return userId;
    }

    public void setUserId(User userId) {

        this.userId = userId;
    }

    public Long getGradePointAverage() {

        return gradePointAverage;
    }

    public void setGradePointAverage(Long gradePointAverage) {

        this.gradePointAverage = gradePointAverage;
    }

    public Position getPosition_id() {

        return position_id;
    }

    public void setPosition_id(Position position_id) {

        this.position_id = position_id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Statement)) return false;
        Statement statement = (Statement) o;
        return Objects.equals(id, statement.id)
                && facultyId.equals(statement.facultyId)
                && userId.equals(statement.userId)
                && gradePointAverage.equals(statement.gradePointAverage)
                && position_id.equals(statement.position_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyId, userId, gradePointAverage, position_id);
    }

    @Override
    public String toString() {
        return "Statement{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", userId=" + userId +
                ", gradePointAverage=" + gradePointAverage +
                ", position_id=" + position_id +
                '}';
    }
}
