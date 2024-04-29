package com.dss.copilot.rent.model.service;

import com.dss.copilot.rent.model.response.ResponseGetPackages;

/**
 * CarService interface.
 * add method getPackages and return ResponseGetPackages object without parameters.
 */
public interface CarService {
    /**
     * getPackages method.
     * @return ResponseGetPackages object.
     */
    ResponseGetPackages getPackages();
}
