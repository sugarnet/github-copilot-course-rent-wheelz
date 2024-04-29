package com.dss.copilot.rent.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * add @Data annotation.
 * add @NoArgsConstructor annotation.
 * add @AllArgsConstructor annotation.
 * add @Builder annotation.
 *
 * add field cars of type List<GetPackagesCarData>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPackagesData {

        private List<GetPackagesCarData> cars;
}
