package com.epam.selectionСommitteeSpring.controller.user;


import com.epam.selectionСommitteeSpring.controller.util.Order;
import com.epam.selectionСommitteeSpring.model.DTO.StatementForm;
import com.epam.selectionСommitteeSpring.model.Entity.Faculty;
import com.epam.selectionСommitteeSpring.model.Entity.Statement;
import com.epam.selectionСommitteeSpring.model.Entity.User;
import com.epam.selectionСommitteeSpring.model.service.FacultyService;
import com.epam.selectionСommitteeSpring.model.service.StatementService;
import com.epam.selectionСommitteeSpring.model.service.SubjectService;
import com.epam.selectionСommitteeSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final FacultyService facultyService;
    private final UserService userService;

    private final StatementService statementService;

    private final SubjectService subjectService;

    @Autowired
    public UserController(UserService userService,
                          FacultyService facultyService,
                          SubjectService subjectService,
                          StatementService statementService) {
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.userService = userService;
        this.statementService = statementService;
    }

    @GetMapping()
    public String userPage(@RequestParam("userId") Long id,
                           Model model) {
        User user = userService.findUserById(id);

        List<Statement>statements = statementService.findAllStatementsByUserId(user);
        System.out.println(user.getUsername() + " " + user.getEmail());

        for (Statement statement: statements  ) {
                   System.out.println(statement.getFacultyId().getName()+"\n"+
               statement.getPosition_id().getPositionType().name());

        }


        model.addAttribute("statements",statements);
        model.addAttribute("userProfile", user);
        return "User/UserPage";
    }

    @GetMapping("/faculties")
    public String getAllFaculties(@RequestParam(name = "sort",required = false,defaultValue = "name") String sort,
                                  @RequestParam(name = "order",required = false,defaultValue = "asc") String order,
                                  Model model) {

        List<Faculty> faculties = facultyService.getAllFaculties();
        List<Faculty> sortedFaculties = Order.facultySorting(faculties,sort,order);

        model.addAttribute("sort", sort);
        model.addAttribute("order",order);
        model.addAttribute("faculties", sortedFaculties);
        return "Admin/faculties";
    }

    @GetMapping("/statement")
    public String statementPage(@RequestParam(name = "facultyId") Long facultyId,
                                  Model model,
                                  Authentication authentication) {

        User user = userService.findByName(authentication.getName());
        Faculty faculty = facultyService.getFaculty(facultyId);


        StatementForm statementForm = new StatementForm();
        statementForm.setUser(user);
        statementForm.setFaculty(faculty);

        model.addAttribute("statementForm",statementForm);
        model.addAttribute("subjectList",faculty.getSubjects());

        return "User/registrationOnFaculty";

    }

    @PostMapping("/statement")
    public String createStatement(@ModelAttribute("statementForm")
                                      StatementForm statementForm,
                                  BindingResult bindingResult,
                                  Model model){
        System.out.println(statementForm.getUser().toString());
        System.out.println(statementForm.getFaculty().toString());

        statementService.addStatement(statementForm);

        return "redirect:/user/faculties";
    }
}
