package com.dss.copilot.rent.model.data;

/**
 * UserLoginData class with fields userName, userEmail and proofId.
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
public class UserLoginData {
    private String userName;
    private String userEmail;
    private String proofId;
}
