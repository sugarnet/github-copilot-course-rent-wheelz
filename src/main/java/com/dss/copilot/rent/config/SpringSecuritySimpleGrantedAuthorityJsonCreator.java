package com.dss.copilot.rent.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SpringSecuritySimpleGrantedAuthorityJsonCreator {
    @JsonCreator
    public SpringSecuritySimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {
    }
}
