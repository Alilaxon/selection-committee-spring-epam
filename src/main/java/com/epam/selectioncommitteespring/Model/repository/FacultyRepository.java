package com.epam.selectioncommitteespring.Model.repository;

import com.epam.selectioncommitteespring.Model.Entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    boolean existsByName(String name);

}
