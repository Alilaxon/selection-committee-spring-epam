package com.epam.selectionСommitteeSpring.model.repository;

import com.epam.selectionСommitteeSpring.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

     Role findByRoleName (Role.RoleName roleName);

}
