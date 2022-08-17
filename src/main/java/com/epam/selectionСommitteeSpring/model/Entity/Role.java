package com.epam.selectionСommitteeSpring.model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public enum RoleName{
        USER,ADMIN
    }

    public Role() {
    }

    public Role(RoleName name) {

        this.roleName = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public RoleName getRoleName() {

        return roleName;
    }

    public void setRoleName(RoleName roleName) {

        this.roleName = roleName;
    }


}
