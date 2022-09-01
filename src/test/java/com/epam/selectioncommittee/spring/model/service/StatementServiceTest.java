package com.epam.selectioncommittee.spring.model.service;


import com.epam.selectioncommittee.spring.model.builders.FacultyBuilder;
import com.epam.selectioncommittee.spring.model.builders.StatementBuilder;
import com.epam.selectioncommittee.spring.model.builders.StatementFormBuilder;
import com.epam.selectioncommittee.spring.model.builders.UserBuilder;
import com.epam.selectioncommittee.spring.model.dto.StatementForm;
import com.epam.selectioncommittee.spring.model.entity.*;
import com.epam.selectioncommittee.spring.model.exception.UserAlreadyRegisteredException;
import com.epam.selectioncommittee.spring.model.repository.PositionRepository;
import com.epam.selectioncommittee.spring.model.repository.StatementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatementServiceTest {
    @Autowired
    StatementService statementService;

    @MockBean
    StatementRepository statementRepository;

    @MockBean
    PositionRepository positionRepository;

    private Statement STATEMENT;

    private StatementForm STATEMENT_FORM;

    private final Long ID = 1l;

    private final Long GPA = 199L;

    private User USER;

    private Faculty FACULTY;

    private Position POSITION = new Position(Position.PositionType.REGISTERED);

    private List<Long> GRADES;

    @BeforeEach
    void setUp() {
        USER = UserBuilder.builder().id(ID).build();
        FACULTY = FacultyBuilder.builder().id(ID).build();
        GRADES = List.of(new Long[]{199L, 199L, 199L});

        STATEMENT = StatementBuilder.builder()
                .userId(USER)
                .facultyId(FACULTY)
                .gradePointAverage(GPA)
                .positionId(POSITION)
                .build();

        STATEMENT_FORM = StatementFormBuilder.builder()
                .user(USER)
                .faculty(FACULTY)
                .grades(GRADES)
                .build();




    }

    @Test
    void addStatement() throws UserAlreadyRegisteredException {
        when(statementRepository.existsByUserIdAndAndFacultyId(USER,FACULTY)).thenReturn(false);
        when(positionRepository.findByPositionType(Position.PositionType.REGISTERED)).thenReturn(POSITION);
        when(statementRepository.save(STATEMENT)).thenReturn(STATEMENT);
        assertEquals(statementService.createStatement(STATEMENT_FORM),STATEMENT);
        verify(statementRepository,times(1)).save(STATEMENT);
    }

    @Test
    void findAllStatementsByUserId() {
        when(statementRepository.findAllByUserId(USER)).thenReturn(List.of(STATEMENT));
        assertEquals(statementService.findAllStatementsByUserId(USER),List.of(STATEMENT));
        verify(statementRepository,times(1)).findAllByUserId(USER);
    }

    @Test
    void findAllStatementsByFaculty() {
        when(statementRepository.findAllByFacultyId(FACULTY)).thenReturn(List.of(STATEMENT));
        assertEquals(statementService.findAllStatementsByFaculty(FACULTY),List.of(STATEMENT));
        verify(statementRepository,times(1)).findAllByFacultyId(FACULTY);
    }
}