package com.epam.selectioncommittee.spring.model.builders;

import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.entity.Position;
import com.epam.selectioncommittee.spring.model.entity.Statement;
import com.epam.selectioncommittee.spring.model.entity.User;



public class StatementBuilder {
    private Long id;

    private Faculty facultyId;

    private User userId;

    private Long gradePointAverage;


    private Position position_id;

    public StatementBuilder() {
    }

    public static StatementBuilder builder()  {
        return new StatementBuilder();
    }

    public StatementBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public StatementBuilder userId(User userId) {
        this.userId = userId;
        return this;
    }

    public StatementBuilder facultyId(Faculty facultyId) {
        this.facultyId = facultyId;
        return this;
    }

    public StatementBuilder gradePointAverage(Long gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
        return this;
    }

    public StatementBuilder positionId(Position position_id) {
        this.position_id = position_id;
        return this;
    }

    public Statement build (){
        return new Statement(id,facultyId,userId,gradePointAverage,position_id);
    }
}
