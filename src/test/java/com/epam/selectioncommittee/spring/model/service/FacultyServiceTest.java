package com.epam.selectioncommittee.spring.model.service;

import com.epam.selectioncommittee.spring.model.builders.FacultyBuilder;
import com.epam.selectioncommittee.spring.model.builders.FacultyFormBuilder;
import com.epam.selectioncommittee.spring.model.builders.SubjectBuilder;
import com.epam.selectioncommittee.spring.model.dto.FacultyForm;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.entity.Subject;
import com.epam.selectioncommittee.spring.model.exception.FacultyIsReservedException;
import com.epam.selectioncommittee.spring.model.repository.FacultyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FacultyServiceTest {

    @Autowired
    FacultyService facultyService;
    @MockBean
    FacultyRepository facultyRepository;


    private Faculty FACULTY;

    private Faculty FACULTY_WITH_ID;

    private FacultyForm FACULTY_FORM;

    private final Long ID = 1L;

    private final String FACULTY_NAME = "Faculty";

    private final String FACULTY_NAME_RU = "Факультет";

    private Subject SUBJECT;

    private final String SUBJECT_NAME_EN = "English";

    private final String SUBJECT_NAME_RU = "Русский";



    @BeforeEach
    void setUp() {
        SUBJECT = SubjectBuilder.builder()
                .nameRU(SUBJECT_NAME_RU)
                .nameEN(SUBJECT_NAME_EN)
                .build();



        FACULTY = FacultyBuilder.builder()
                .facultyName(FACULTY_NAME)
                .facultyNameRU(FACULTY_NAME_RU)
                .generalPlaces(10)
                .budgetPlaces(5)
                .requiredSubjects(List.of(SUBJECT))
                .recruitment(false)
                .build();

        FACULTY_WITH_ID = FacultyBuilder.builder()
                .facultyName(FACULTY_NAME)
                .facultyNameRU(FACULTY_NAME_RU)
                .generalPlaces(10)
                .budgetPlaces(5)
                .requiredSubjects(List.of(SUBJECT))
                .recruitment(false)
                .build();

        FACULTY_FORM = FacultyFormBuilder.builder()
                .facultyName(FACULTY_NAME)
                .facultyNameRU(FACULTY_NAME_RU)
                .generalPlaces(10)
                .budgetPlaces(5)
                .requiredSubjects(List.of(SUBJECT))
                .recruitment(false)
                .build();
    }

    @Test
    void addFaculty() throws FacultyIsReservedException {
        when(facultyRepository.existsByName(FACULTY_NAME)).thenReturn(false);
        when(facultyRepository.save(FACULTY)).thenReturn(FACULTY);
        assertEquals(facultyService.addFaculty(FACULTY_FORM), FACULTY);

    }

    @Test
    void addFacultyThrowsFacultyIsReservedException() {

        when(facultyRepository.existsByName(FACULTY_NAME)).thenReturn(true);
        assertThrows(FacultyIsReservedException.class,()->facultyService.addFaculty(FACULTY_FORM));
    }

    @Test
    void getAllFaculties() {
        when(facultyRepository.findAll()).thenReturn(List.of(FACULTY));
        assertEquals(facultyService.getAllFaculties(),List.of(FACULTY));
    }

    @Test
    void deleteFaculty() {

    }

    @Test
    void getFaculty() {
        when(facultyRepository.findById(ID)).thenReturn(Optional.of(FACULTY));
        assertEquals(facultyService.getFaculty(ID), FACULTY);

    }

    @Test
    void updateFaculty() throws FacultyIsReservedException {
        when(facultyRepository.existsByName(FACULTY_NAME)).thenReturn(false);
        when(facultyRepository.save(FACULTY_WITH_ID)).thenReturn(FACULTY_WITH_ID);
        assertEquals(facultyService.updateFaculty((FACULTY_FORM)), FACULTY_WITH_ID);

    }

    @Test
    void updateFacultyThrowsFacultyIsReservedException() {

        when(facultyRepository.existsByName(FACULTY_NAME)).thenReturn(true);
        assertThrows(FacultyIsReservedException.class,()->facultyService.updateFaculty(FACULTY_FORM));
    }

    @Test
    void closeFacultyById() {
        when(facultyRepository.findById(ID)).thenReturn(Optional.of(FACULTY));
        when(facultyRepository.save(FACULTY)).thenReturn(FACULTY);
        assertEquals(facultyService.closeFacultyById(ID).getRecruitment(),true);
    }

    @Test
    void openFacultyById() {
        when(facultyRepository.findById(ID)).thenReturn(Optional.of(FACULTY));
        when(facultyRepository.save(FACULTY)).thenReturn(FACULTY);
        assertEquals(facultyService.openFacultyById(ID).getRecruitment(),false);
    }


}