package com.dss.copilot.rent.model.request;

/**
 * add field userEmail. add NotEmpty annotation.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 *
 */
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyBookingsRequest {

    @NotEmpty
    private String userEmail;
}
