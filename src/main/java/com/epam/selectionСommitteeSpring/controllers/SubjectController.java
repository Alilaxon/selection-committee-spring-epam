package com.epam.selectionСommitteeSpring.controllers;

import com.epam.selectionСommitteeSpring.controllers.util.Sorter;
import com.epam.selectionСommitteeSpring.controllers.util.SubjectUrl;
import com.epam.selectionСommitteeSpring.model.entity.Subject;
import com.epam.selectionСommitteeSpring.model.dto.SubjectForm;
import com.epam.selectionСommitteeSpring.model.exception.SubjectIsReservedException;
import com.epam.selectionСommitteeSpring.model.service.SubjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class SubjectController {

    private static final Logger log = LogManager.getLogger(SubjectController.class);
    private final SubjectService subjectService;
    @Autowired
    public SubjectController(SubjectService subjectService) {

        this.subjectService = subjectService;
    }

    @GetMapping(SubjectUrl.SUBJECTS)
    public String allSubjects(@RequestParam(name = "page", required = false, defaultValue = "1") String page,
                              @RequestParam(name = "size", required = false, defaultValue = "3") String size,
                              Model model) {

        Page<Subject> subjectPage = subjectService.getAllSubjectsPage(Sorter.subjectSorting( page, size));

        model.addAttribute("subjects", subjectPage.getContent())
                .addAttribute("size", size)
                .addAttribute("page", page)
                .addAttribute("pages", subjectPage.getTotalPages());



        return "admin/subjects";

    }

    @GetMapping(SubjectUrl.ADD_SUBJECT)
    public String registrationSubject(@ModelAttribute("subjectFrom")
                                      SubjectForm subjectForm,
                                      Model model) {

        return "/admin/createSubject";

    }

    @PostMapping(SubjectUrl.ADD_SUBJECT)
    public String addSubject(@ModelAttribute("subjectFrom")
                             @Valid SubjectForm subjectForm,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {

            return "/admin/createSubject";

        } try {
            subjectService.createSubject(subjectForm);

            return "redirect:/admin/subjects";

        } catch (SubjectIsReservedException exception) {

            model.addAttribute("SubjectIsReserved", true);
            log.warn("Subject name '{}' is reserved", subjectForm.getNameEN());

            return "/admin/createSubject";
        }
    }

    @DeleteMapping(SubjectUrl.DELETE_FACULTY)
    public String deleteSubject(@PathVariable("id") Long id) {

        subjectService.deleteSubject(id);
        return "redirect:/admin/subjects";
    }
}
