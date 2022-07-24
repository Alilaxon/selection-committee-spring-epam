package com.epam.selectioncommitteespring.Model.repository;

import com.epam.selectioncommitteespring.Model.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    boolean existsByNameEN(String nameEN);



}
