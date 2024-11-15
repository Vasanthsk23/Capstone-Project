package com.capstone.project.busbooking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<BusEntity,Long> {
    List<BusEntity> findByBoardingPointAndDestination(String boardingPoint, String destination);
}
