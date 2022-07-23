package com.epam.selectioncommitteespring.Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_en")
    private String nameEN;

    @Column(name = "name_ru")
    private String nameRU;



    public Subject() {
    }

    public Subject(String nameEN,
                   String nameRU
                   ) {

        this.nameEN = nameEN;
        this.nameRU = nameRU;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getNameEN() {

        return nameEN;
    }

    public void setNameEN(String nameEN) {

        this.nameEN = nameEN;
    }

    public String getNameRU() {

        return nameRU;
    }

    public void setNameRU(String nameRU) {

        this.nameRU = nameRU;
    }


}
