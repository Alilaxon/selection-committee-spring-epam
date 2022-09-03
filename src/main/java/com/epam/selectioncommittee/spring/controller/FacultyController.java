package com.epam.selectioncommittee.spring.controller;

import com.epam.selectioncommittee.spring.controller.util.FacultyUrl;
import com.epam.selectioncommittee.spring.controller.util.Sorter;
import com.epam.selectioncommittee.spring.controller.util.Validator;
import com.epam.selectioncommittee.spring.model.builders.FacultyFormBuilder;
import com.epam.selectioncommittee.spring.model.exception.FacultyIsReservedException;
import com.epam.selectioncommittee.spring.model.service.StatementService;
import com.epam.selectioncommittee.spring.model.service.SubjectService;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.dto.FacultyForm;
import com.epam.selectioncommittee.spring.model.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class FacultyController {

    private static final Logger log = LogManager.getLogger(FacultyController.class);

    private final SubjectService subjectService;

    private final FacultyService facultyService;




    @Autowired
    public FacultyController(SubjectService subjectService, FacultyService facultyService) {
        this.subjectService = subjectService;
        this.facultyService = facultyService;

    }

    @GetMapping(FacultyUrl.FACULTIES)
    public String getAllFaculties(@RequestParam(name = "sort", required = false, defaultValue = "name") String sort,
                                  @RequestParam(name = "order", required = false, defaultValue = "asc") String order,
                                  Model model) {

        model.addAttribute("sort", sort)
                .addAttribute("order", order)
                .addAttribute("faculties", Sorter.facultySorting(facultyService.getAllFaculties(), sort, order));

        return "admin/faculties";
    }

    @GetMapping(FacultyUrl.CREATE_FACULTY)
    public String registrationFaculty(Model model) {

        model.addAttribute("subjectList", subjectService.getAllSubjects())
                .addAttribute("facultyForm", new FacultyForm());

        return "admin/createFaculty";
    }

    @PostMapping(FacultyUrl.CREATE_FACULTY)
    public String addFaculty(@ModelAttribute("facultyForm")
                             @Valid FacultyForm facultyForm,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors() || Validator.facultyValid(facultyForm)) {

            //Cписок предметов посли ошибки валидации
            model.addAttribute("subjectList", subjectService.getAllSubjects());
            model.addAttribute("hasErrors", true);
            return "admin/createFaculty";
        }
        try {
            facultyService.addFaculty(facultyForm);

            return "redirect:/faculties";

        } catch (FacultyIsReservedException exception) {

            model.addAttribute("FacultyIsReserved", true)
                    .addAttribute("subjectList", subjectService.getAllSubjects());

            log.warn("Faculty name '{}' is reserved", facultyForm.getFacultyName());

            return "admin/createFaculty";
        }
    }

    @DeleteMapping({FacultyUrl.DELETE_FACULTY})
    public String deleteFaculty(@PathVariable("id") Long id) {

        facultyService.deleteFaculty(id);

        return "redirect:/faculties";
    }

    @GetMapping(FacultyUrl.UPDATE_FACULTY)
    public String updateFaculty(@RequestParam("facultyId") Long facultyId, Model model) {

        Faculty faculty = facultyService.getFaculty(facultyId);

        model.addAttribute("subjects", subjectService.getAllSubjects())
                .addAttribute("facultyForm", FacultyFormBuilder.builder()
                        .id(faculty.getId())
                        .facultyName(faculty.getName())
                        .budgetPlaces(faculty.getBudgetPlaces())
                        .generalPlaces(faculty.getGeneralPlaces())
                        .requiredSubjects(faculty.getSubjects())
                        .recruitment(faculty.getRecruitment())
                        .build());

        return "admin/updateFaculty";
    }

    @PatchMapping(FacultyUrl.UPDATE_FACULTY)
    public String patchFaculty(@Valid FacultyForm facultyForm,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors() || Validator.facultyValid(facultyForm)) {

            model.addAttribute("hasErrors", true)
                    .addAttribute("facultyForm", facultyForm)
                    .addAttribute("subjects", subjectService.getAllSubjects());

            return "admin/updateFaculty";
        }

        try {
            facultyService.updateFaculty(facultyForm);

            return "redirect:/faculties";

        } catch (FacultyIsReservedException exception) {
            model.addAttribute("FacultyIsReserved", true)
                    .addAttribute("facultyForm", facultyForm)
                    .addAttribute("subjects", subjectService.getAllSubjects());

            log.warn("Faculty name '{}' is reserved", facultyForm.getFacultyName());

            return "admin/updateFaculty";
        }

    }


}
