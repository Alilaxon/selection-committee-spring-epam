package com.epam.selectioncommittee.spring.model.service;

import com.epam.selectioncommittee.spring.model.builders.SubjectBuilder;
import com.epam.selectioncommittee.spring.model.builders.SubjectFormBuilder;
import com.epam.selectioncommittee.spring.model.dto.SubjectForm;
import com.epam.selectioncommittee.spring.model.entity.Subject;
import com.epam.selectioncommittee.spring.model.exception.SubjectIsReservedException;
import com.epam.selectioncommittee.spring.model.exception.UsernameIsReservedException;
import com.epam.selectioncommittee.spring.model.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class SubjectServiceTest {

    @Autowired
    SubjectService subjectService;

    @MockBean
    SubjectRepository subjectRepository;

    private Subject SUBJECT;

    private SubjectForm SUBJECT_FORM;

    private String NAME_EN = "English";

    private String NAME_RU = "Русский";

    @BeforeEach
    void setUp() {
        SUBJECT = SubjectBuilder.builder()
                .nameEN(NAME_EN)
                .nameRU(NAME_RU)
                .build();

        SUBJECT_FORM = SubjectFormBuilder.builder()
                .nameEN(NAME_EN)
                .nameRU(NAME_RU)
                .build();


    }

    @Test
    void createSubject() throws SubjectIsReservedException {
        when(subjectRepository.existsByNameEN(NAME_EN)).thenReturn(false);
        when(subjectRepository.save(SUBJECT)).thenReturn(SUBJECT);
        assertEquals(subjectService.createSubject(SUBJECT_FORM),SUBJECT);
        verify(subjectRepository,times(1)).save(SUBJECT);


    }

    @Test
    void createSubjectThrowsSubjectIsReservedException() {
        when(subjectRepository.existsByNameEN(NAME_EN)).thenReturn(true);
        assertThrows(SubjectIsReservedException.class ,() -> subjectService.createSubject(SUBJECT_FORM));
    }


    @Test
    void getAllSubjects() {
        when(subjectRepository.findAll()).thenReturn(List.of(SUBJECT));
        assertEquals(subjectService.getAllSubjects(),List.of(SUBJECT));
        verify(subjectRepository,times(1)).findAll();
    }
}