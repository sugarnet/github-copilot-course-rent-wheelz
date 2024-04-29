package com.dss.copilot.rent.model.response;

import com.dss.copilot.rent.model.data.MyBookingsData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ResponseMyBookings class extends Response class.
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseMyBookings extends Response {

    private MyBookingsData data;
}
