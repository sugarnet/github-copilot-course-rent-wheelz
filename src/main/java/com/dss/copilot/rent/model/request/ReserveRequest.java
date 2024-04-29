package com.dss.copilot.rent.model.request;

/**
 * ReserveRequest class.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 * add fields pickupDate, returnDate, numOfTravellers and carId
 */

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveRequest {
    @NotNull
    private Date pickupDate;
    @NotNull
    private Date returnDate;
    @Positive
    private Integer numOfTravellers;
    @NotNull
    private Long carId;
}
