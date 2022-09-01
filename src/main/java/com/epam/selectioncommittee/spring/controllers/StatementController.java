package com.epam.selectioncommittee.spring.controllers;

import com.epam.selectioncommittee.spring.model.service.StatementService;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class StatementController {

    private static final Logger log = LogManager.getLogger(StatementController.class);
    private  final StatementService statementService;
    private  final FacultyService facultyService;
    @Autowired
    public StatementController(StatementService statementService, FacultyService facultyService) {
        this.statementService = statementService;
        this.facultyService = facultyService;
    }

    @GetMapping("/admin/statements")
    public String getStatements (@RequestParam("facultyId") Long facultyId, Model model){

        Faculty faculty = facultyService.getFaculty(facultyId);

        model.addAttribute("statements",statementService.findAllStatementsByFaculty(faculty))
             .addAttribute("faculty",faculty);

        log.info("admin check '{}' statements",faculty.getName());

        return "admin/statements";
    }
}
