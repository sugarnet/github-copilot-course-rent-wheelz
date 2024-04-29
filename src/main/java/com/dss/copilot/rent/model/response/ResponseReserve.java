package com.dss.copilot.rent.model.response;

/**
 * ResponseReserve class extends Response class.
 * add private ReserveData data.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 *
 */

import com.dss.copilot.rent.model.data.ReserveData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseReserve extends Response {
    private ReserveData data;
}
