package com.epam.selectionСommitteeSpring.model.service;


import com.epam.selectionСommitteeSpring.controllers.util.AverageGrade;
import com.epam.selectionСommitteeSpring.model.dto.StatementForm;
import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.entity.Position;
import com.epam.selectionСommitteeSpring.model.entity.Statement;
import com.epam.selectionСommitteeSpring.model.entity.User;
import com.epam.selectionСommitteeSpring.model.exception.UserAlreadyRegisteredException;
import com.epam.selectionСommitteeSpring.model.repository.PositionRepository;
import com.epam.selectionСommitteeSpring.model.repository.StatementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementService {

    private static final Logger log = LogManager.getLogger(StatementService.class);
    StatementRepository statementRepository;
    PositionRepository positionRepository;


    @Autowired
    public StatementService(StatementRepository statementRepository, PositionRepository positionRepository) {
        this.statementRepository = statementRepository;
        this.positionRepository = positionRepository;
    }

    public void addStatement(StatementForm statementForm) throws UserAlreadyRegisteredException {
        checkIfRegistered(statementForm);

        Statement statement = new Statement();
        statement.setUserId(statementForm.getUser());
        statement.setFacultyId(statementForm.getFaculty());
        statement.setPosition_id(positionRepository.findByPositionType(Position.PositionType.REGISTERED));
        statement.setGradePointAverage(AverageGrade.counter(statementForm.getGrades()));

        log.info("User '{}'  created statement", statementForm.getUser().getUsername());

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

        log.info("faculty '{}'  finalized results", faculty.getName());

        statementRepository.saveAll(statements);

    }
    private void checkIfRegistered(StatementForm statementForm) throws UserAlreadyRegisteredException {

        if(statementRepository.existsByUserIdAndAndFacultyId(statementForm.getUser(),statementForm.getFaculty())){
            throw new UserAlreadyRegisteredException();
        }

    }
    public boolean checkIfRegistered(User user, Faculty faculty)  {

        return statementRepository.existsByUserIdAndAndFacultyId(user,faculty);

    }
}
