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

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyBookingsData {
    List<MyBookingData> bookings;
}
