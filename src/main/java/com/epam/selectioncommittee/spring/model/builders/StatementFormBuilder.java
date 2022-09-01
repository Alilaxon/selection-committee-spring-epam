package com.epam.selectioncommittee.spring.model.builders;

import com.epam.selectioncommittee.spring.model.dto.StatementForm;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.entity.User;

import java.util.List;

public class StatementFormBuilder {

    User user;

    Faculty faculty;

    List<Long> grades;

    public StatementFormBuilder() {
    }

    public static StatementFormBuilder builder() {
        return new StatementFormBuilder();
    }

    public StatementFormBuilder user(User user) {
        this.user = user;
        return this;
    }

    public StatementFormBuilder faculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }

    public StatementFormBuilder grades(List<Long> grades) {
        this.grades = grades;
        return this;
    }

    public StatementForm build(){
        return new StatementForm(user,faculty,grades);
    }
}
