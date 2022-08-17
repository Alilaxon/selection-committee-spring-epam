package com.epam.selectionСommitteeSpring.model.service;


import com.epam.selectionСommitteeSpring.controller.util.AverageGrade;
import com.epam.selectionСommitteeSpring.model.DTO.StatementForm;
import com.epam.selectionСommitteeSpring.model.Entity.Faculty;
import com.epam.selectionСommitteeSpring.model.Entity.Position;
import com.epam.selectionСommitteeSpring.model.Entity.Statement;
import com.epam.selectionСommitteeSpring.model.Entity.User;
import com.epam.selectionСommitteeSpring.model.repository.PositionRepository;
import com.epam.selectionСommitteeSpring.model.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementService {

    StatementRepository statementRepository;
    PositionRepository positionRepository;


    @Autowired
    public StatementService(StatementRepository statementRepository, PositionRepository positionRepository) {
        this.statementRepository = statementRepository;
        this.positionRepository = positionRepository;
    }

    public void addStatement(StatementForm statementForm){
        Statement statement = new Statement();
        statement.setUserId(statementForm.getUser());
        statement.setFacultyId(statementForm.getFaculty());
        statement.setPosition_id(positionRepository.findByPositionType(Position.PositionType.REGISTERED));
        statement.setGradePointAverage(AverageGrade.counter(statementForm.getGrades()));

        statementRepository.save(statement);
    }


    public List<Statement> findAllStatementsByUserId(User userid){
        return statementRepository.findAllByUserId(userid);
    }

    public List<Statement> findAllStatementsByFaculty (Faculty facultyId){
        return statementRepository.findAllByFacultyId(facultyId);
    }

    public void finalizeStatements(Faculty faculty){

        List<Statement> statements = findAllStatementsByFaculty(faculty)
                .stream()
                .sorted(Comparator.comparing(Statement::getGradePointAverage))
                .collect(Collectors.toList());



        statements.stream()
                .limit(faculty.getGeneralPlaces())
                .forEach(statement -> statement.setPosition_id(
                        positionRepository.findByPositionType(Position.PositionType.CONTRACT)
                ));

        statements.stream()
                .limit(faculty.getBudgetPlaces())
                .forEach(statement -> statement.setPosition_id(
                        positionRepository.findByPositionType(Position.PositionType.BUDGET)
                ));


        statementRepository.saveAll(statements);

    }
}
