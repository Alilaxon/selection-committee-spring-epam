package com.epam.selectionСommitteeSpring.model.repository;

import com.epam.selectionСommitteeSpring.model.entity.Faculty;
import com.epam.selectionСommitteeSpring.model.entity.Statement;
import com.epam.selectionСommitteeSpring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement,Long> {

   List <Statement> findAllByUserId(User userid);

   List<Statement> findAllByFacultyId(Faculty facultyId);

   boolean existsByUserIdAndAndFacultyId(User user,Faculty faculty);
   @Modifying
   @Query("delete from Statement s where s.userId.id=:user and s.facultyId.id <> :faculty")
   void deleteAllByUserIdAndFacultyId(@Param("user") Long user,@Param("faculty")Long faculty);

}
