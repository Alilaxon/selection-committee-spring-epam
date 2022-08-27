package com.epam.selectionСommitteeSpring.controllers;

import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.service.FacultyService;
import com.epam.selectionСommitteeSpring.model.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class StatementController {

    StatementService statementService;

    FacultyService facultyService;
    @Autowired
    public StatementController(StatementService statementService, FacultyService facultyService) {
        this.statementService = statementService;
        this.facultyService = facultyService;
    }

    @GetMapping("/admin/statements")
    public String getStatements (@RequestParam("facultyId") Long facultyId, Model model){
        Faculty faculty = facultyService.getFaculty(facultyId);

        model.addAttribute("statements",statementService.findAllStatementsByFaculty(faculty))
             .addAttribute("facultyName",faculty.getName());

        return "admin/statements";
    }
}
