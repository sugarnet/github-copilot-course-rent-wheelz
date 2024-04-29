package com.dss.copilot.rent.model.request;

/**
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 * add fields userEmail, userPassword, userName and proofId
 * set @NotEmpty annotation for userEmail, userPassword, userName and proofId
 */

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequest {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String userName;
    @NotBlank
    private String proofId;
}
