package com.epam.selectioncommittee.spring.model.builders;

import com.epam.selectioncommittee.spring.model.dto.SubjectForm;

public class SubjectFormBuilder {

    private String nameEN;

    private String nameRU;

    public SubjectFormBuilder() {
    }


    public static SubjectFormBuilder builder() {

        return new SubjectFormBuilder();

    }

    public SubjectFormBuilder nameEN(String nameEN) {
        this.nameEN = nameEN;
        return this;

    }

    public SubjectFormBuilder nameRU(String nameRU) {
        this.nameRU = nameRU;
        return this;

    }
    public SubjectForm build(){
        return new SubjectForm(nameEN,nameRU);
    }
}
