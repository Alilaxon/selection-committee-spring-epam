package com.epam.selectioncommitteespring.Model.repository;

import com.epam.selectioncommitteespring.Model.Entity.Statement;
import com.epam.selectioncommitteespring.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement,Long> {

   List <Statement> findAllByUserId(User userid);

}
