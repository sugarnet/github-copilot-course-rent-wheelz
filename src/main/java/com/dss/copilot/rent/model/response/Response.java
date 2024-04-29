package com.dss.copilot.rent.model.response;

/**
 * Response class implements Serializable.
 * add private String message and private String status.
 * add @Data annotation
 * add @NoArgsConstructor annotation
 * add @AllArgsConstructor annotation
 * add @Builder annotation
 *
 */

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Response implements Serializable {
    private String message;
    private String status;
}
