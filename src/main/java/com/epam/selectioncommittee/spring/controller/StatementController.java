package com.epam.selectioncommittee.spring.controller;

import com.epam.selectioncommittee.spring.controller.util.FacultyUrl;
import com.epam.selectioncommittee.spring.controller.util.Sorter;
import com.epam.selectioncommittee.spring.model.entity.Statement;
import com.epam.selectioncommittee.spring.model.entity.Subject;
import com.epam.selectioncommittee.spring.model.service.StatementService;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StatementController {

    private static final Logger log = LogManager.getLogger(StatementController.class);
    private final StatementService statementService;
    private final FacultyService facultyService;

    @Autowired
    public StatementController(StatementService statementService, FacultyService facultyService) {
        this.statementService = statementService;
        this.facultyService = facultyService;
    }

    @GetMapping("/admin/statements")
    public String getStatements(@RequestParam("facultyId") Long facultyId,
                                @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                @RequestParam(name = "size", required = false, defaultValue = "5") String size,
                                @RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
                                @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                                Model model) {

        Faculty faculty = facultyService.getFaculty(facultyId);
        Page<Statement> statements =
                statementService.findAllStatementsByFaculty(faculty, Sorter.userSorting(order, sort, page, size));

        model.addAttribute("statements", statements.getContent())
                .addAttribute("faculty", faculty)
                .addAttribute("size", size)
                .addAttribute("page", page)
                .addAttribute("pages", statements.getTotalPages())
                .addAttribute("sort", sort)
                .addAttribute("order", order);

        log.info("admin check '{}' statements", faculty.getName());

        return "admin/statements";
    }

    @DeleteMapping({"/statement/{id}"})
    public String deleteStatement(@PathVariable("id") Long id) {

        statementService.deleteStatement(id);

        return "user/deleteStatement";
    }
}
