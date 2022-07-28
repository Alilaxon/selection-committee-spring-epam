package com.epam.selectioncommitteespring.Controller.Admin;


import com.epam.selectioncommitteespring.Model.DTO.FacultyForm;
import com.epam.selectioncommitteespring.Model.DTO.SubjectForm;
import com.epam.selectioncommitteespring.Model.Entity.Faculty;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            @RequestParam("userId") Long id, Model model) {

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

    @PatchMapping("/users")
    public String blockUsers(@RequestParam("userId") Long userId,
                             @RequestParam("userBlocked") Boolean blocked) {
        if (blocked) {
            userService.unblockUserById(userId);
        } else {
            userService.blockUserById(userId);
        }
        return "redirect:/admin/users";
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
    public String deleteSubject(@PathVariable("id") Long id) {

        System.out.println("id=" + id + " delete");

        subjectService.deleteSubject(id);
        return "redirect:/admin/subjects";
    }

    @PostMapping("/addSubject")
    public String addSubject(@ModelAttribute("subjectFrom")
                             @Valid SubjectForm subjectForm,
                             BindingResult bindingResult,
                             Model model) {

        System.out.println("addSubject: " + subjectForm.getNameEN());

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
    public String getAllFaculties(@RequestParam(name = "sort",required = false,defaultValue = "name")
                                      String sort, Model model) {

       List<Faculty> faculties = facultyService.getAllFaculties();
       List<Faculty> sortedFaculties;
        System.out.println(sort.equals("generalCapacity"));
       if(sort.equals("generalPlaces")){

        sortedFaculties = faculties.stream().sorted(Comparator.comparing(Faculty::getGeneralPlaces))
                .collect(Collectors.toList());

       } else if (sort.equals("budgetPlaces")) {

           sortedFaculties = faculties.stream().sorted(Comparator.comparing(Faculty::getBudgetPlaces))
                   .collect(Collectors.toList());

       } else {

           sortedFaculties = faculties.stream().sorted(Comparator.comparing(Faculty::getName))
                   .collect(Collectors.toList());
       }

        model.addAttribute("sort", sort);
        model.addAttribute("faculties", sortedFaculties);

        return "admin/AllFaculties";
    }


    @GetMapping("/addFaculty")
    public String registrationFaculty(Model model) {

        List<Subject> subjects = subjectService.getAllSubjects();

        model.addAttribute("subjectList", subjects);
        model.addAttribute("facultyForm", new FacultyForm());

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
    public String deleteFaculty(@PathVariable("id") Long id) {

        System.out.println("id=" + id + " delete faculty");
        facultyService.deleteFaculty(id);

        return "redirect:/admin/faculties";
    }

    @GetMapping("/updateFaculty/{id}")
    public String updateFaculty(@PathVariable("id") Long facultyId, Model model) {
        Faculty faculty = facultyService.getFaculty(facultyId);
        List<Subject> subjects = subjectService.getAllSubjects();

        FacultyForm facultyForm = new FacultyForm();
        facultyForm.setId(faculty.getId());
        facultyForm.setFacultyName(faculty.getName());
        facultyForm.setGeneralPlaces(faculty.getGeneralPlaces());
        facultyForm.setBudgetPlaces(faculty.getBudgetPlaces());
        facultyForm.setRequiredSubjects(faculty.getSubjects());

        model.addAttribute("facultyForm", facultyForm);
        model.addAttribute("subjects", subjects);

        return "admin/UpdateFaculty";
    }

    @PatchMapping("/editFaculty")
    public String patchFaculty(@Valid FacultyForm facultyForm,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/UpdateFaculty";
        }
        facultyService.updateFaculty(facultyForm);

        return "redirect:/admin/faculties";
    }
}
