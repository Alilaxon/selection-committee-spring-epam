package com.epam.selectioncommittee.spring.controllers.util;

public class FacultyUrl {

    private FacultyUrl() {
    }

    public static final String CREATE_FACULTY = "/admin/addFaculty";

    public static final String FACULTIES = "/faculties";

    public static final String UPDATE_FACULTY = "/admin/updateFaculty";

    public static final String DELETE_FACULTY = "/admin/faculty/{id}";


}
