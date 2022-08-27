package com.epam.selectionСommitteeSpring.model.repository;

import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    boolean existsByName(String name);

    Faculty findByName(String name);

    boolean existsByNameAndAndId(String name,Long id);




}
