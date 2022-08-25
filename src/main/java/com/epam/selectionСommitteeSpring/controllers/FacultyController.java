package com.epam.selectionСommitteeSpring.controllers;

import com.epam.selectionСommitteeSpring.controllers.util.FacultyUrl;
import com.epam.selectionСommitteeSpring.controllers.util.Sorter;
import com.epam.selectionСommitteeSpring.controllers.util.enums.FacultyUrls;
import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.entity.Subject;
import com.epam.selectionСommitteeSpring.model.builders.FacultyFormBuilder;
import com.epam.selectionСommitteeSpring.model.dto.FacultyForm;
import com.epam.selectionСommitteeSpring.model.exception.FacultyIsReservedException;
import com.epam.selectionСommitteeSpring.model.service.FacultyService;
import com.epam.selectionСommitteeSpring.model.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class FacultyController {

   private final SubjectService subjectService;

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(SubjectService subjectService, FacultyService facultyService) {
        this.subjectService = subjectService;
        this.facultyService = facultyService;
    }




    @GetMapping(FacultyUrl.FACULTIES)
    public String getAllFaculties(@RequestParam(name = "sort",required = false,defaultValue = "name") String sort,
                                  @RequestParam(name = "order",required = false,defaultValue = "asc") String order,
                                  Model model) {

        List<Faculty> sortedFaculties = Sorter.facultySorting(facultyService.getAllFaculties(),sort,order);

        model.addAttribute("sort", sort);
        model.addAttribute("order",order);
        model.addAttribute("faculties", sortedFaculties);
        return "admin/faculties";
    }

    @GetMapping(FacultyUrl.CREATE_FACULTY)
    public String registrationFaculty(Model model) {

        List<Subject> subjects = subjectService.getAllSubjects();

        model.addAttribute("subjectList", subjects)
             .addAttribute("facultyForm", new FacultyForm());

        return "admin/createFaculty";
    }

    @PostMapping(FacultyUrl.CREATE_FACULTY)
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
            return "admin/createFaculty";
        }
        try {
            facultyService.addFaculty(facultyForm);

            return "redirect:/faculties";

        } catch (FacultyIsReservedException exception) {
            model.addAttribute("FacultyIsReserved", true);

            return "admin/createFaculty";
        }
    }

    @DeleteMapping({FacultyUrl.DELETE_FACULTY})
    public String deleteFaculty(@PathVariable("id") Long id) {

        System.out.println("id=" + id + " delete faculty");
        facultyService.deleteFaculty(id);

        return "redirect:/faculties";
    }

    @GetMapping(FacultyUrl.UPDATE_FACULTY)
    public String updateFaculty(@RequestParam("facultyId") Long facultyId, Model model) {

        Faculty faculty = facultyService.getFaculty(facultyId);
        List<Subject> subjects = subjectService.getAllSubjects();

        FacultyForm facultyForm = FacultyFormBuilder.builder()
                .id(faculty.getId())
                .facultyName(faculty.getName())
                .budgetPlaces(faculty.getBudgetPlaces())
                .generalPlaces(faculty.getGeneralPlaces())
                .requiredSubjects(faculty.getSubjects())
                .recruitment(faculty.getRecruitment())
                .build();

        model.addAttribute("subjects", subjects)
             .addAttribute("facultyForm", facultyForm);


        return "admin/updateFaculty";
    }

    @PatchMapping(FacultyUrl.UPDATE_FACULTY)
    public String patchFaculty(@Valid FacultyForm facultyForm,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors() || facultyForm.getGeneralPlaces() < facultyForm.getBudgetPlaces()) {
            model.addAttribute("subjects", facultyForm.getRequiredSubjects());

            return "admin/updateFaculty";
        }
        facultyService.updateFaculty(facultyForm);

        return "redirect:/faculties";
    }


}
