package com.dss.copilot.rent.model.repository;

/**
 * ReservationDao interface extends JpaRepository with Reservation entity and String as generic type.
 * add method findByUserEmail to find a reservations by user email.
 * add method to find reservations by car id and date grater than or equal to pickup date and less than or equal to return date
 *
 */

import com.dss.copilot.rent.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationDao extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserEmail(String userEmail);

    List<Reservation> findByCarIdAndPickupDateGreaterThanEqualAndReturnDateLessThanEqual(Long carId, Date pickupDate, Date returnDate);
}   // End of ReservationDao interface