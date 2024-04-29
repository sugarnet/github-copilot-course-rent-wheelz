package com.dss.copilot.rent.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * replicate fields from Car entity. Fields: id, model, brand, status, registrationNumber, pricePerHour, thumbnail
 * add @Data annotation.
 * add @NoArgsConstructor annotation.
 * add @AllArgsConstructor annotation.
 * add @Builder annotation.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPackagesCarData {

    private Long id;
    private String model;
    private String brand;
    private boolean status;
    private String registrationNumber;
    private double pricePerHour;
    private String thumbnail;

}
