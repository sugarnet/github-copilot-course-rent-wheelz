package com.dss.copilot.rent.model.response;

import com.dss.copilot.rent.model.data.GetPackagesData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ResponseGetPackages class.
 * add @Data annotation.
 * add @NoArgsConstructor annotation.
 * add @AllArgsConstructor annotation.
 * add @Builder annotation.
 * <p>
 * extend Response class.
 * <p>
 * add field data of type GetPackagesData.
 * add field results of type int.
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseGetPackages extends Response {

    private GetPackagesData data;
    private int results;
}


