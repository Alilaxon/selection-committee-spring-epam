package com.epam.selectioncommitteespring.Model.Entity;


import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;



    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    PositionType positionType;

    public Position() {
    }

    public enum PositionType{
        REGISTERED,
        CONTRACT,
        BUDGET,
        REJECTED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }
}
