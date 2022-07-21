package com.epam.selectioncommitteespring.Controller.Admin;


import com.epam.selectioncommitteespring.Model.DTO.SubjectForm;
import com.epam.selectioncommitteespring.Model.DTO.UserForm;
import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.exception.SubjectIsReservedException;
import com.epam.selectioncommitteespring.Model.service.SubjectService;
import com.epam.selectioncommitteespring.Model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    SubjectService subjectService;
    UserService userService;


    @Autowired
    public AdminController(UserService userService,
                           SubjectService subjectService) {

        this.userService = userService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public String adminPage(
            @RequestParam(value = "userId",
                    required = false,
                    defaultValue = "4") Long id, Model model) {

        User user = userService.findUserById(id);

        System.out.println(user.getUsername() + " " + user.getEmail());
        model.addAttribute("userProfile", user);

        return "Admin/AdminPage";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {
        System.out.println(userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());

        return "admin/AllUsers";
    }

    @GetMapping("/subjects")
    public String allSubjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());

        return "admin/AllSubjects";
    }

    @GetMapping("/AddNewSubject")
    public String registrationSubject(@ModelAttribute("subjectFrom") SubjectForm subjectForm, Model model) {

        return "admin/AddNewSubject";
    }


    @PostMapping("/addSubject")
    public String addSubject(@ModelAttribute("subjectFrom")
                             @Valid SubjectForm subjectForm,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {

            return "admin/AddNewSubject";
        }

        try {
            subjectService.addNewSubject(subjectForm);
            return "redirect:/admin";

        } catch (SubjectIsReservedException exception) {
            model.addAttribute("SubjectIsReserved", true);
            return "admin/AddNewSubject";
        }
    }
}
