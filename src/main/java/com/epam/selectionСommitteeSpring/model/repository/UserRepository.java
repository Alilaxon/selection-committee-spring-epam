package com.epam.selectionСommitteeSpring.model.repository;

import com.epam.selectionСommitteeSpring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsUserByUsername(String username);

    boolean existsByEmail (String email);

    User findByUsername(String username);

    @Query( "select u from User u  where u.role.roleName='USER'" )
    List<User> findAllByRole();

}
