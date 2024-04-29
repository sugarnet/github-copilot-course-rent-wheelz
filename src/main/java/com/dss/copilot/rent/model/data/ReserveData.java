package com.dss.copilot.rent.model.data;

/**
 * ReserveData class.
 * add fields bookingId and userEmail.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 *
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveData {
    private Long bookingId;
    private String userEmail;
}
