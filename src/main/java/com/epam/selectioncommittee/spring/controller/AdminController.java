package com.epam.selectioncommittee.spring.controller;


import com.epam.selectioncommittee.spring.model.service.StatementService;
import com.epam.selectioncommittee.spring.model.entity.User;
import com.epam.selectioncommittee.spring.model.service.FacultyService;
import com.epam.selectioncommittee.spring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

   private final UserService userService;
    private final  FacultyService facultyService;
    private final StatementService statementService;

    @Autowired
    public AdminController(UserService userService, FacultyService facultyService, StatementService statementService) {
        this.facultyService = facultyService;
        this.userService = userService;
        this.statementService = statementService;
    }
    @GetMapping()
    public String adminPage(@RequestParam("id") Long id, Model model) {

        User user = userService.findUserById(id);

        model.addAttribute("userProfile", user);

        return "admin/AdminPage";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "admin/users";
    }

    @PatchMapping("/user")
    public String blockUsers(@RequestParam("userId") Long userId,
                             @RequestParam("userBlocked") boolean blocked) {
        if (blocked) {
            userService.unblockUserById(userId);
        } else {
            userService.blockUserById(userId);
        }
        return "redirect:/admin/users";
    }
    @PatchMapping("/recruitment")
    public String openCloseFaculty(@RequestParam("facultyId") Long facultyId,
                                   @RequestParam("facultyOpen") boolean recruit) {
        if (recruit) {
            facultyService.openFacultyById(facultyId);
        } else {
            facultyService.closeFacultyById(facultyId);
            statementService.finalizeStatements(facultyService.getFaculty(facultyId));

        }
        return "redirect:/faculties";
    }

}
