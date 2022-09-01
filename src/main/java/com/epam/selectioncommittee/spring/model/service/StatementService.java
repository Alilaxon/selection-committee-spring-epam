package com.epam.selectioncommittee.spring.model.service;


import com.epam.selectioncommittee.spring.controllers.util.AverageGrade;
import com.epam.selectioncommittee.spring.model.builders.StatementBuilder;
import com.epam.selectioncommittee.spring.model.dto.StatementForm;
import com.epam.selectioncommittee.spring.model.repository.PositionRepository;
import com.epam.selectioncommittee.spring.model.repository.StatementRepository;
import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.entity.Position;
import com.epam.selectioncommittee.spring.model.entity.Statement;
import com.epam.selectioncommittee.spring.model.entity.User;
import com.epam.selectioncommittee.spring.model.exception.UserAlreadyRegisteredException;
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
    private  final StatementRepository statementRepository;
    private  final PositionRepository positionRepository;


    @Autowired
    public StatementService(StatementRepository statementRepository,
                            PositionRepository positionRepository) {
        this.statementRepository = statementRepository;
        this.positionRepository = positionRepository;
    }

    public Statement createStatement(StatementForm statementForm) throws UserAlreadyRegisteredException {
        checkIfRegistered(statementForm);

        log.info("User '{}'  created statement", statementForm.getUser().getUsername());

        return statementRepository.save(StatementBuilder.builder()
                .userId(statementForm.getUser())
                .facultyId(statementForm.getFaculty())
                .positionId(positionRepository.findByPositionType(Position.PositionType.REGISTERED))
                .gradePointAverage(AverageGrade.counter(statementForm.getGrades()))
                .build());
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

        for (Statement statement: statements) {
           if(isOnFaculty(statement)){

               log.info("all other statements of '{}' on '{}' will be delete"
                       ,statement.getUserId().getUsername()
                       ,statement.getFacultyId().getName());

               deleteOtherStatements(statement);
           }
        }
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

    private boolean isOnFaculty (Statement statement){
        return  statement.getPosition_id().getPositionType().equals(Position.PositionType.BUDGET) ||
                statement.getPosition_id().getPositionType().equals(Position.PositionType.CONTRACT);
    }

    private void deleteOtherStatements (Statement statement){

        statementRepository.deleteAll( statementRepository
                .findAllByUserId(statement.getUserId()).stream()
                .filter(i ->i.getFacultyId().getId() != statement.getFacultyId().getId())
                .collect(Collectors.toList()));

    }

}
