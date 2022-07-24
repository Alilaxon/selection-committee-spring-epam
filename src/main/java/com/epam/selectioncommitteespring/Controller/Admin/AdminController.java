package com.epam.selectioncommitteespring.Controller.Admin;


import com.epam.selectioncommitteespring.Model.DTO.FacultyForm;
import com.epam.selectioncommitteespring.Model.DTO.SubjectForm;
import com.epam.selectioncommitteespring.Model.DTO.UserForm;
import com.epam.selectioncommitteespring.Model.Entity.Subject;
import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.exception.FacultyIsReservedException;
import com.epam.selectioncommitteespring.Model.exception.SubjectIsReservedException;
import com.epam.selectioncommitteespring.Model.service.FacultyService;
import com.epam.selectioncommitteespring.Model.service.SubjectService;
import com.epam.selectioncommitteespring.Model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    SubjectService subjectService;
    UserService userService;
    FacultyService facultyService;


    @Autowired
    public AdminController(UserService userService,
                           SubjectService subjectService,
                           FacultyService facultyService) {
        this.facultyService = facultyService;
        this.userService = userService;
        this.subjectService = subjectService;
    }

    @GetMapping()
    public String adminPage(
            @RequestParam(value = "userId",
                    required = false,
                    defaultValue = "2") Long id, Model model) {

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

    @GetMapping("/addSubject")
    public String registrationSubject(@ModelAttribute("subjectFrom")
                                          SubjectForm subjectForm,
                                          Model model) {

        return "admin/AddNewSubject";
    }

    @DeleteMapping({"{id}"})
    public String deleteSubject (@PathVariable("id") Long id){

        System.out.println("id="+id+" delete");

        subjectService.deleteSubject(id);
        return "redirect:/admin/subjects";
    }

    @PostMapping("/addSubject")
    public String addSubject(@ModelAttribute("subjectFrom")
                             @Valid SubjectForm subjectForm,
                             BindingResult bindingResult,
                             Model model) {

        System.out.println("addSubject: "+subjectForm.getNameEN());

        if (bindingResult.hasErrors()) {

            return "admin/AddNewSubject";
        }

        try {
            subjectService.addNewSubject(subjectForm);
            return "redirect:/admin/subjects";

        } catch (SubjectIsReservedException exception) {
            model.addAttribute("SubjectIsReserved", true);
            return "admin/AddNewSubject";
        }
    }
    @GetMapping("/faculties")
    public String getAllFaculties(Model model){
        model.addAttribute("faculties",facultyService.getAllFaculties());
    return "admin/AllFaculties";
    }



    @GetMapping("/addFaculty")
    public String registrationFaculty(Model model){

        List<Subject> subjects = subjectService.getAllSubjects();

        model.addAttribute("subjectList",subjects);
        model.addAttribute("facultyForm",new FacultyForm());


        return "admin/AddNewFaculty";
    }

    @PostMapping("/addFaculty")
    public String addFaculty(@ModelAttribute("facultyForm")
                             @Valid FacultyForm facultyForm,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/AddNewFaculty";
        }
        try {
            facultyService.addFaculty(facultyForm);
            return "redirect:/admin/faculties";

        } catch (FacultyIsReservedException exception) {
            model.addAttribute("FacultyIsReserved", true);
            return "admin/AddNewFaculty";
        }
    }
    @DeleteMapping({"/faculty/{id}"})
    public String deleteFaculty (@PathVariable("id") Long id){

        System.out.println("id="+id+" delete faculty");

        facultyService.deleteFaculty(id);
        return "redirect:/admin/faculties";
    }
}
