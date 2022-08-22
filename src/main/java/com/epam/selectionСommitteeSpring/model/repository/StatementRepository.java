package com.epam.selectionСommitteeSpring.model.repository;

import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.entity.Statement;
import com.epam.selectionСommitteeSpring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement,Long> {

   List <Statement> findAllByUserId(User userid);

   List<Statement> findAllByFacultyId(Faculty facultyId);

   boolean existsByUserIdAndAndFacultyId(User user,Faculty faculty);

}
