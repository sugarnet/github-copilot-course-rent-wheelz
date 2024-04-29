package com.dss.copilot.rent.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Reservation entity with fields:
 * bookingId, userName, userEmail, carID, reservationDate, pickupDate, returnDate, numOfTravellers, status, car, img, total
 * add @Entity annotation
 * add @Builder annotation
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Id annotation ti bookingId
 * add @GeneratedValue(strategy = GenerationType.UUID) annotation to bookingId
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private String userName;
    private String userEmail;
    private Long carId;
    private Date reservationDate;
    private Date pickupDate;
    private Date returnDate;
    private Integer numOfTravellers;
    private String status;
    private String car;
    private String img;
    private Double total;
}
