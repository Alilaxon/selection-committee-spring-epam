package com.epam.selectioncommitteespring.Controller.User;


import com.epam.selectioncommitteespring.Model.DTO.StatementForm;
import com.epam.selectioncommitteespring.Model.Entity.Faculty;
import com.epam.selectioncommitteespring.Model.Entity.Statement;
import com.epam.selectioncommitteespring.Model.Entity.Subject;
import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.service.FacultyService;
import com.epam.selectioncommitteespring.Model.service.StatementService;
import com.epam.selectioncommitteespring.Model.service.SubjectService;
import com.epam.selectioncommitteespring.Model.service.UserService;
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
    public String getAllFaculties(Model model) {

        model.addAttribute("faculties", facultyService.getAllFaculties());
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
