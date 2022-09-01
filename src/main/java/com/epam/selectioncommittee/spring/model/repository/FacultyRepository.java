package com.epam.selectioncommittee.spring.model.repository;

import com.epam.selectioncommittee.spring.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    boolean existsByName(String name);

    Faculty findByName(String name);

}
