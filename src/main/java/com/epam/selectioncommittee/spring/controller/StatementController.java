package com.epam.selectioncommittee.spring.controller;

import com.epam.selectioncommittee.spring.controller.util.FacultyUrl;
import com.epam.selectioncommittee.spring.controller.util.Sorter;
import com.epam.selectioncommittee.spring.controller.util.StatementUrl;
import com.epam.selectioncommittee.spring.model.dto.StatementForm;
import com.epam.selectioncommittee.spring.model.entity.Statement;
import com.epam.selectioncommittee.spring.model.entity.Subject;
import com.epam.selectioncommittee.spring.model.entity.User;
import com.epam.selectioncommittee.spring.model.exception.UserAlreadyRegisteredException;
import com.epam.selectioncommittee.spring.model.service.StatementService;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.service.FacultyService;
import com.epam.selectioncommittee.spring.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StatementController {

    private static final Logger log = LogManager.getLogger(StatementController.class);
    private final StatementService statementService;
    private final FacultyService facultyService;

    private UserService userService;

    @Autowired
    public StatementController(StatementService statementService, FacultyService facultyService,UserService userService) {
        this.userService = userService;
        this.statementService = statementService;
        this.facultyService = facultyService;
    }

    //@GetMapping("/admin/statements")
    @GetMapping(StatementUrl.STATEMENTS)
    public String getStatements(@RequestParam("facultyId") Long facultyId,
                                @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                @RequestParam(name = "size", required = false, defaultValue = "5") String size,
                                @RequestParam(name = "sort", required = false, defaultValue = "id") String sort,
                                @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                                Model model) {

        Faculty faculty = facultyService.getFaculty(facultyId);
        Page<Statement> statements = statementService
                .findAllStatementsByFaculty(faculty, Sorter.userSorting(order, sort, page, size));

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

    //@DeleteMapping({"/statement/{id}"})
    @DeleteMapping(StatementUrl.DELETE_STATEMENT)
    public String deleteStatement(@PathVariable("id") Long id) {

        statementService.deleteStatement(id);

        return "user/deleteStatement";
    }

    //@GetMapping("/user/statement")
    @GetMapping(StatementUrl.CREATE_STATEMENT)
    public String getCreateStatement(@RequestParam(name = "facultyId") Long facultyId,
                                     Model model, Authentication authentication) {

        User user = userService.findByName(authentication.getName());


        Faculty faculty = facultyService.getFaculty(facultyId);



        if (statementService.checkIfRegistered(user, faculty)) {

            return "user/alreadyRegistered";
        }

        StatementForm statementForm = new StatementForm();
        statementForm.setUser(user);
        statementForm.setFaculty(faculty);

        model.addAttribute("statementForm", statementForm);
        model.addAttribute("subjectList", faculty.getSubjects());

        return "user/createStatement";

    }

    //@PostMapping("/user/statement")
    @PostMapping(StatementUrl.CREATE_STATEMENT)
    public String postCreateStatement(@ModelAttribute("statementForm")
                                      StatementForm statementForm, Model model)  {
        try {
            statementService.createStatement(statementForm);

            return "redirect:/faculties";

        } catch (UserAlreadyRegisteredException exception) {

            model.addAttribute("UserAlreadyRegistered", true);
        }
        return "user/createStatement";
    }
}
