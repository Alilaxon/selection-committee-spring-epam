package com.epam.selectionСommitteeSpring.model.builders;

import com.epam.selectionСommitteeSpring.model.entity.Subject;

import javax.persistence.Column;

public class SubjectBuilder {
    private Long id;

    private String nameEN;

    private String nameRU;

    public static UserBuilder builder(){

        return new UserBuilder();
    }

    public SubjectBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public SubjectBuilder nameEN(String nameEN) {
        this.nameEN = nameEN;
        return this;
    }

    public SubjectBuilder nameRU(String nameRU) {
        this.nameRU = nameRU;
        return this;
    }

    public Subject build(){

        return new Subject(id,nameEN,nameRU);
    }

}
