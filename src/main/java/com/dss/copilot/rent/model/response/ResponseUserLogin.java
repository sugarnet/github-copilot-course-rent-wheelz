package com.dss.copilot.rent.model.response;

/**
 * Response class extends Response class.
 * add private UserLoginData data.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 *
 */

import com.dss.copilot.rent.model.data.UserLoginData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseUserLogin extends Response {
    private UserLoginData data;
}