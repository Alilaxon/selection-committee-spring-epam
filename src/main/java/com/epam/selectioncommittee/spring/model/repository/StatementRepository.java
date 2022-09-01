package com.epam.selectioncommittee.spring.model.repository;

import com.epam.selectioncommittee.spring.model.entity.Faculty;
import com.epam.selectioncommittee.spring.model.entity.Statement;
import com.epam.selectioncommittee.spring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

    List<Statement> findAllByUserId(User userid);

    List<Statement> findAllByFacultyId(Faculty facultyId);

    boolean existsByUserIdAndAndFacultyId(User user, Faculty faculty);

}
