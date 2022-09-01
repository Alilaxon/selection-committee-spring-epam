package com.epam.selectioncommittee.spring.controllers.util;

import com.epam.selectioncommittee.spring.model.dto.FacultyForm;

public class Validator {
    public static boolean facultyValid(FacultyForm facultyForm){
        return facultyForm.getBudgetPlaces() > facultyForm.getGeneralPlaces()
                || facultyForm.getRequiredSubjects().size() < 2;
    }
}
