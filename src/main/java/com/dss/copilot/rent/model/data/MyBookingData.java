package com.dss.copilot.rent.model.data;

/**
 * MyBookingsData class.
 * add fields bookingId, userEmail, carId, reservationDate, pickupDate, returnDate, numOfTravellers, status, car, img and total.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyBookingData {
    private Long bookingId;
    private String userEmail;
    private Long carId;
    private String reservationDate;
    private String pickupDate;
    private String returnDate;
    private Integer numOfTravellers;
    private String status;
    private String car;
    private String img;
    private Double total;
}
