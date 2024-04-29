package com.dss.copilot.rent.model.service.impl;

import com.dss.copilot.rent.model.data.GetPackagesCarData;
import com.dss.copilot.rent.model.data.GetPackagesData;
import com.dss.copilot.rent.model.entity.Car;
import com.dss.copilot.rent.model.repository.CarDao;
import com.dss.copilot.rent.model.response.ResponseGetPackages;
import com.dss.copilot.rent.model.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CarServiceImpl class.
 * add @Service annotation.
 * implement CarService interface.
 * inject CarDao using constructor injection.
 */
@Service
public class CarServiceImpl implements CarService {
    private final CarDao carDao;

    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Transactional
    @Override
    public ResponseGetPackages getPackages() {
        // use findAll method of carDao to get all cars. Set it to a List<Car> object.
        // validate if the list is empty. If it is, create a Response object with status success and message "No packages found".
        // If the list is not empty, create a GetPackagesData object with the list of cars and the size of the list in results fields.
        // return a ResponseGetPackages object with the GetPackagesData object and the size of the list in results field.

        List<Car> cars = carDao.findAll();
        ResponseGetPackages response = new ResponseGetPackages();
        if (cars.isEmpty()) {
            response.setStatus("success");
            response.setMessage("No packages found");
            return response;
        }
        List<GetPackagesCarData> carsData = cars.stream()
                .map(car -> GetPackagesCarData.builder()
                        .id(car.getCarID())
                        .model(car.getCarModel())
                        .brand(car.getBrand())
                        .status(car.isCarAvailability())
                        .registrationNumber(car.getRegistrationNumber())
                        .pricePerHour(car.getPricePerHour())
                        .thumbnail(car.getThumbnail())
                        .build())
                .collect(Collectors.toList());

        response.setData(GetPackagesData.builder().cars(carsData).build());
        response.setResults(cars.size());
        response.setStatus("success");

        return response;
    }
}
