package com.epam.selectionСommitteeSpring.controllers.user;


import com.epam.selectionСommitteeSpring.model.dto.StatementForm;
import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.entity.Statement;
import com.epam.selectionСommitteeSpring.model.entity.User;
import com.epam.selectionСommitteeSpring.model.exception.UserAlreadyRegisteredException;
import com.epam.selectionСommitteeSpring.model.service.FacultyService;
import com.epam.selectionСommitteeSpring.model.service.StatementService;

import com.epam.selectionСommitteeSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final FacultyService facultyService;

    private final UserService userService;

    private final StatementService statementService;


    @Autowired
    public UserController(UserService userService,
                          FacultyService facultyService,
                          StatementService statementService) {

        this.facultyService = facultyService;
        this.userService = userService;
        this.statementService = statementService;
    }

    @GetMapping()
    public String userPage(@RequestParam("userId") Long id,
                           Model model) {
        User user = userService.findUserById(id);

        List<Statement> statements = statementService.findAllStatementsByUserId(user);

        model.addAttribute("statements", statements);
        model.addAttribute("userProfile", user);
        return "User/UserPage";
    }

    @GetMapping("/statement")
    public String statementPage(@RequestParam(name = "facultyId") Long facultyId,
                                Model model,
                                Authentication authentication) {


        User user = userService.findByName(authentication.getName());
        Faculty faculty = facultyService.getFaculty(facultyId);

        if (statementService.checkIfRegistered(user, faculty)) {

            return "User/alreadyRegistered";
        }

        StatementForm statementForm = new StatementForm();
        statementForm.setUser(user);
        statementForm.setFaculty(faculty);

        model.addAttribute("statementForm", statementForm);
        model.addAttribute("subjectList", faculty.getSubjects());

        return "User/registrationOnFaculty";

    }

    @PostMapping("/statement")
    public String createStatement(@ModelAttribute("statementForm")
                                  StatementForm statementForm,
                                  Model model)  {

        try {
            statementService.addStatement(statementForm);
            return "redirect:/faculties";

        } catch (UserAlreadyRegisteredException exception) {
            model.addAttribute("UserAlreadyRegistered", true);
        }
        return "User/registrationOnFaculty";
    }
    //    @GetMapping("/faculties")
//    public String getAllFaculties(@RequestParam(name = "sort",required = false,defaultValue = "name") String sort,
//                                  @RequestParam(name = "order",required = false,defaultValue = "asc") String order,
//                                  Model model) {
//
//        List<Faculty> faculties = facultyService.getAllFaculties();
//        List<Faculty> sortedFaculties = Sorter.facultySorting(faculties,sort,order);
//
//        model.addAttribute("sort", sort);
//        model.addAttribute("order",order);
//        model.addAttribute("faculties", sortedFaculties);
//        return "admin/faculties";
//    }
}
