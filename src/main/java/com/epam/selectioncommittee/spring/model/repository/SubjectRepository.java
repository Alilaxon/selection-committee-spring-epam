package com.epam.selectioncommittee.spring.model.repository;

import com.epam.selectioncommittee.spring.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    boolean existsByNameEN(String nameEN);

}
