package com.dss.copilot.rent.model.repository;

/**
 * CarDao interface extends JpaRepository with Car entity and Long as generic type.
 */
import com.dss.copilot.rent.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car, Long> {
}   // End of CarDao interface
