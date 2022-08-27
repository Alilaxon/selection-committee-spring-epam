package com.epam.selectionСommitteeSpring.controllers.util;

import com.epam.selectionСommitteeSpring.model.dto.FacultyForm;

public class Validator {
    public static boolean facultyValid(FacultyForm facultyForm){
        return facultyForm.getBudgetPlaces() > facultyForm.getGeneralPlaces()
                && facultyForm.getRequiredSubjects().size() < 2;
    }
}
