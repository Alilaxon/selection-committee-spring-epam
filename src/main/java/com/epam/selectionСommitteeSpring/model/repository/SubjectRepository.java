package com.epam.selectionСommitteeSpring.model.repository;

import com.epam.selectionСommitteeSpring.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    boolean existsByNameEN(String nameEN);



}
