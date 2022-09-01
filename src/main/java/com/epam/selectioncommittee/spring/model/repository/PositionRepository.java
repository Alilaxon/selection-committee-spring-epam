package com.epam.selectioncommittee.spring.model.repository;

import com.epam.selectioncommittee.spring.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByPositionType(Position.PositionType type);
}
