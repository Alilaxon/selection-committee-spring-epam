package com.epam.selectioncommitteespring.Model.service;


import com.epam.selectioncommitteespring.Model.DTO.StatementForm;
import com.epam.selectioncommitteespring.Model.Entity.Position;
import com.epam.selectioncommitteespring.Model.Entity.Statement;
import com.epam.selectioncommitteespring.Model.Entity.User;
import com.epam.selectioncommitteespring.Model.repository.PositionRepository;
import com.epam.selectioncommitteespring.Model.repository.StatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        statement.setGradePointAverage(150L);

        statementRepository.save(statement);
    }


    public List<Statement> findAllStatementsByUserId(User userid){
        return statementRepository.findAllByUserId(userid);
    }


}
