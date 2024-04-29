package com.dss.copilot.rent.controller;

import com.dss.copilot.rent.model.response.ResponseGetPackages;
import com.dss.copilot.rent.model.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CarController class.
 * add @RestController annotation
 * add method: getPackages with @GetMapping("/getPackages") annotation. Return ResponseEntity with
 * injected CarService using constructor injection and call getPackages method from CarService.
 *
 */
@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * getPackages method.
     * @return ResponseEntity with ResponseGetPackages object.
     */
    @GetMapping("/getPackages")
    public ResponseEntity<ResponseGetPackages> getPackages() {
        ResponseGetPackages packagesResponse = carService.getPackages();
        return new ResponseEntity<>(packagesResponse, packagesResponse.getStatus().equals("error")
                ? HttpStatus.BAD_REQUEST
                : HttpStatus.OK);
    }


}
