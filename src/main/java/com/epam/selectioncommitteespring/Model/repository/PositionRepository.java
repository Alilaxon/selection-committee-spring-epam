package com.epam.selectioncommitteespring.Model.repository;

import com.epam.selectioncommitteespring.Model.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    Position findByPositionType(Position.PositionType type);
}
