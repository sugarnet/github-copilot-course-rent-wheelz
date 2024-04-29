package com.dss.copilot.rent.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Car entity.
 * add attributes: carID, carModel, registrationNumber, carAvailability, brand, pricePerHour, thumbnail
 * add @Entity annotation
 * add @Builder annotation
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 *
 * add @Id annotation
 * add @GeneratedValue(strategy = GenerationType.IDENTITY) annotation
 * add @Column(name = "car_id") annotation
 *
 *
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carID;
    private String carModel;
    @Column(unique = true)
    private String registrationNumber;
    private boolean carAvailability;
    private String brand;
    private double pricePerHour;
    private String thumbnail;
}
