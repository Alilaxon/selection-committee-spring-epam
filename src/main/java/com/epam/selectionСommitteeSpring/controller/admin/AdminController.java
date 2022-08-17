package com.epam.selectionСommitteeSpring.controller.admin;


import com.epam.selectionСommitteeSpring.controller.util.Order;
import com.epam.selectionСommitteeSpring.model.DTO.FacultyForm;
import com.epam.selectionСommitteeSpring.model.DTO.SubjectForm;
import com.epam.selectionСommitteeSpring.model.Entity.Faculty;
import com.epam.selectionСommitteeSpring.model.Entity.Subject;
import com.epam.selectionСommitteeSpring.model.Entity.User;
import com.epam.selectionСommitteeSpring.model.exception.FacultyIsReservedException;
import com.epam.selectionСommitteeSpring.model.exception.SubjectIsReservedException;
import com.epam.selectionСommitteeSpring.model.service.FacultyService;
import com.epam.selectionСommitteeSpring.model.service.StatementService;
import com.epam.selectionСommitteeSpring.model.service.SubjectService;
import com.epam.selectionСommitteeSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Стоит разделить этот контроллер , на отдельный для факультетов и предметов
@Controller
@RequestMapping("/admin")
public class
AdminController {
    SubjectService subjectService;
    UserService userService;
    FacultyService facultyService;
    StatementService statementService;


    @Autowired
    public AdminController(UserService userService,
                           SubjectService subjectService,
                           FacultyService facultyService,
                           StatementService statementService) {
        this.facultyService = facultyService;
        this.userService = userService;
        this.subjectService = subjectService;
        this.statementService = statementService;
    }

    @GetMapping()
    public String adminPage(
            @RequestParam("userId") Long id, Model model) {

        User user = userService.findUserById(id);


        model.addAttribute("userProfile", user);

        return "Admin/AdminPage";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "Admin/users";
    }

    @PatchMapping("/user")
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
    public String allSubjects(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
                              @RequestParam(name = "size", required = false, defaultValue = "3") String size,
                              Model model) {

        Page<Subject> subjectPage = subjectService.getAllSubjectsPage(Order.subjectSorting( page, size));

        model.addAttribute("subjects", subjectPage.getContent())
                .addAttribute("size", size)
                .addAttribute("page", page)
                .addAttribute("pages", subjectPage.getTotalPages());

        return "Admin/subjects";
    }

    @GetMapping("/addSubject")
    public String registrationSubject(@ModelAttribute("subjectFrom")
                                      SubjectForm subjectForm,
                                      Model model) {

        return "Admin/AddNewSubject";
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

            return "Admin/AddNewSubject";
        }

        try {
            subjectService.addNewSubject(subjectForm);

            return "redirect:/admin/subjects";

        } catch (SubjectIsReservedException exception) {
            model.addAttribute("SubjectIsReserved", true);

            return "Admin/AddNewSubject";
        }
    }

    @GetMapping("/faculties")
    public String getAllFaculties(@RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
                                  @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                                  Model model) {


        List<Faculty> sortedFaculties = Order.facultySorting(facultyService.getAllFaculties(), sort, order);

        model.addAttribute("sort", sort);
        model.addAttribute("order", order);
        model.addAttribute("faculties", sortedFaculties);

        return "Admin/faculties";
    }


    @GetMapping("/addFaculty")
    public String registrationFaculty(Model model) {

        List<Subject> subjects = subjectService.getAllSubjects();

        model.addAttribute("subjectList", subjects);
        model.addAttribute("facultyForm", new FacultyForm());

        return "Admin/AddNewFaculty";
    }

    @PostMapping("/addFaculty")
    public String addFaculty(@ModelAttribute("facultyForm")
                             @Valid FacultyForm facultyForm,
                             BindingResult bindingResult,
                             Model model) {
        //надо будет сделать отдельный валидатор для создания факультета
        if (bindingResult.hasErrors()
                || facultyForm.getBudgetPlaces() > facultyForm.getGeneralPlaces()
                || facultyForm.getRequiredSubjects().size() < 2) {

            //Cписок предметов посли ошибки валидации
            model.addAttribute("subjectList", subjectService.getAllSubjects());
            return "Admin/AddNewFaculty";
        }
        try {
            facultyService.addFaculty(facultyForm);

            return "redirect:/admin/faculties";

        } catch (FacultyIsReservedException exception) {
            model.addAttribute("FacultyIsReserved", true);

            return "Admin/AddNewFaculty";
        }
    }

    @DeleteMapping({"/faculty/{id}"})
    public String deleteFaculty(@PathVariable("id") Long id) {

        System.out.println("id=" + id + " delete faculty");
        facultyService.deleteFaculty(id);

        return "redirect:/admin/faculties";
    }

    @GetMapping("/updateFaculty")
    public String updateFaculty(@RequestParam("facultyId") Long facultyId, Model model) {
        Faculty faculty = facultyService.getFaculty(facultyId);
        List<Subject> subjects = subjectService.getAllSubjects();

        FacultyForm facultyForm = new FacultyForm();
        facultyForm.setId(faculty.getId());
        facultyForm.setFacultyName(faculty.getName());
        facultyForm.setGeneralPlaces(faculty.getGeneralPlaces());
        facultyForm.setBudgetPlaces(faculty.getBudgetPlaces());
        facultyForm.setRequiredSubjects(faculty.getSubjects());
        facultyForm.setRecruitment(faculty.getRecruitment());

        model.addAttribute("facultyForm", facultyForm);
        model.addAttribute("subjects", subjects);

        return "Admin/UpdateFaculty";
    }

    @PatchMapping("/updateFaculty")
    public String patchFaculty(@Valid FacultyForm facultyForm,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors() ||
                facultyForm.getGeneralPlaces() < facultyForm.getBudgetPlaces()) {
            model.addAttribute("subjects", facultyForm.getRequiredSubjects());
            return "Admin/UpdateFaculty";
        }
        facultyService.updateFaculty(facultyForm);

        return "redirect:/admin/faculties";
    }

    @PatchMapping("/recruitment")
    public String recruitmentOnFaculty(@RequestParam("facultyId") Long facultyId,
                                       @RequestParam("facultyOpen") boolean recruit) {
        if (recruit) {
            facultyService.openFacultyById(facultyId);
        } else {
            facultyService.closeFacultyById(facultyId);
            statementService.finalizeStatements(facultyService.getFaculty(facultyId));

        }
        return "redirect:/admin/faculties";
    }
}
