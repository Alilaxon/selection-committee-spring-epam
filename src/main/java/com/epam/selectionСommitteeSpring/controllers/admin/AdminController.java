package com.epam.selectionСommitteeSpring.controllers.admin;


import com.epam.selectionСommitteeSpring.model.entity.User;
import com.epam.selectionСommitteeSpring.model.service.FacultyService;
import com.epam.selectionСommitteeSpring.model.service.StatementService;
import com.epam.selectionСommitteeSpring.model.service.SubjectService;
import com.epam.selectionСommitteeSpring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "admin/AdminPage";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {

        model.addAttribute("users", userService.getAllUsers());

        return "admin/users";
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
    @PatchMapping("/recruitment")
    public String recruitmentOnFaculty(@RequestParam("facultyId") Long facultyId,
                                       @RequestParam("facultyOpen") boolean recruit) {
        if (recruit) {
            facultyService.openFacultyById(facultyId);
        } else {
            facultyService.closeFacultyById(facultyId);
            statementService.finalizeStatements(facultyService.getFaculty(facultyId));

        }
        return "redirect:/faculties";
    }

//    @GetMapping("/subjects")
//    public String allSubjects(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
//                              @RequestParam(name = "size", required = false, defaultValue = "3") String size,
//                              Model model) {
//
//        Page<Subject> subjectPage = subjectService.getAllSubjectsPage(Sorter.subjectSorting( page, size));
//
//        model.addAttribute("subjects", subjectPage.getContent())
//                .addAttribute("size", size)
//                .addAttribute("page", page)
//                .addAttribute("pages", subjectPage.getTotalPages());
//
//        return "Admin/subjects";
//    }

//    @GetMapping("/addSubject")
//    public String registrationSubject(@ModelAttribute("subjectFrom")
//                                      SubjectForm subjectForm,
//                                      Model model) {
//
//        return "Admin/AddNewSubject";
//    }

//    @DeleteMapping({"{id}"})
//    public String deleteSubject(@PathVariable("id") Long id) {
//
//        System.out.println("id=" + id + " delete");
//
//        subjectService.deleteSubject(id);
//        return "redirect:/admin/subjects";
//    }

//    @PostMapping("/addSubject")
//    public String addSubject(@ModelAttribute("subjectFrom")
//                             @Valid SubjectForm subjectForm,
//                             BindingResult bindingResult,
//                             Model model) {
//
//        System.out.println("addSubject: " + subjectForm.getNameEN());
//
//        if (bindingResult.hasErrors()) {
//
//            return "Admin/AddNewSubject";
//        }
//
//        try {
//            subjectService.addNewSubject(subjectForm);
//
//            return "redirect:/admin/subjects";
//
//        } catch (SubjectIsReservedException exception) {
//            model.addAttribute("SubjectIsReserved", true);
//
//            return "Admin/AddNewSubject";
//        }
//    }

//    @GetMapping("/faculties")
//    public String getAllFaculties(@RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
//                                  @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
//                                  Model model) {
//
//
//        List<Faculty> sortedFaculties = Sorter.facultySorting(facultyService.getAllFaculties(), sort, order);
//
//        model.addAttribute("sort", sort);
//        model.addAttribute("order", order);
//        model.addAttribute("faculties", sortedFaculties);
//
//        return "admin/faculties";
//    }


//    @GetMapping("/addFaculty")
//    public String registrationFaculty(Model model) {
//
//        List<Subject> subjects = subjectService.getAllSubjects();
//
//        model.addAttribute("subjectList", subjects);
//        model.addAttribute("facultyForm", new FacultyForm());
//
//        return "admin/AddNewFaculty";
//    }

//    @PostMapping("/addFaculty")
//    public String addFaculty(@ModelAttribute("facultyForm")
//                             @Valid FacultyForm facultyForm,
//                             BindingResult bindingResult,
//                             Model model) {
//        //надо будет сделать отдельный валидатор для создания факультета
//        if (bindingResult.hasErrors()
//                || facultyForm.getBudgetPlaces() > facultyForm.getGeneralPlaces()
//                || facultyForm.getRequiredSubjects().size() < 2) {
//
//            //Cписок предметов посли ошибки валидации
//            model.addAttribute("subjectList", subjectService.getAllSubjects());
//            return "admin/AddNewFaculty";
//        }
//        try {
//            facultyService.addFaculty(facultyForm);
//
//            return "redirect:/admin/faculties";
//
//        } catch (FacultyIsReservedException exception) {
//            model.addAttribute("FacultyIsReserved", true);
//
//            return "admin/AddNewFaculty";
//        }
//    }

//    @DeleteMapping({"/faculty/{id}"})
//    public String deleteFaculty(@PathVariable("id") Long id) {
//
//        System.out.println("id=" + id + " delete faculty");
//        facultyService.deleteFaculty(id);
//
//        return "redirect:/admin/faculties";
//    }

//    @GetMapping("/updateFaculty")
//    public String updateFaculty(@RequestParam("facultyId") Long facultyId, Model model) {
//        Faculty faculty = facultyService.getFaculty(facultyId);
//        List<Subject> subjects = subjectService.getAllSubjects();
//
//        FacultyForm facultyForm = new FacultyForm();
//        facultyForm.setId(faculty.getId());
//        facultyForm.setFacultyName(faculty.getName());
//        facultyForm.setGeneralPlaces(faculty.getGeneralPlaces());
//        facultyForm.setBudgetPlaces(faculty.getBudgetPlaces());
//        facultyForm.setRequiredSubjects(faculty.getSubjects());
//        facultyForm.setRecruitment(faculty.getRecruitment());
//
//        model.addAttribute("facultyForm", facultyForm);
//        model.addAttribute("subjects", subjects);
//
//        return "admin/UpdateFaculty";
//    }

//    @PatchMapping("/updateFaculty")
//    public String patchFaculty(@Valid FacultyForm facultyForm,
//                               BindingResult bindingResult,
//                               Model model) {
//        if (bindingResult.hasErrors() ||
//                facultyForm.getGeneralPlaces() < facultyForm.getBudgetPlaces()) {
//            model.addAttribute("subjects", facultyForm.getRequiredSubjects());
//            return "admin/UpdateFaculty";
//        }
//        facultyService.updateFaculty(facultyForm);
//
//        return "redirect:/admin/faculties";
//    }


}
