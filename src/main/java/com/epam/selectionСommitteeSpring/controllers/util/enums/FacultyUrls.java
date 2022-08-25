package com.epam.selectionСommitteeSpring.controllers.util.enums;

public enum FacultyUrls {
    CREATE_FACULTY ("/admin/addFaculty"),

    FACULTIES ("/faculties"),

    UPDATE_FACULTY("/admin/updateFaculty"),

    DELETE_FACULTY ("/admin/faculty/{id}");


    private String value;

    FacultyUrls(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
