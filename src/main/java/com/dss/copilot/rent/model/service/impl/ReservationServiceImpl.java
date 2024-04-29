package com.dss.copilot.rent.model.service.impl;

import com.dss.copilot.rent.constant.Constants;
import com.dss.copilot.rent.model.data.MyBookingData;
import com.dss.copilot.rent.model.data.MyBookingsData;
import com.dss.copilot.rent.model.data.ReserveData;
import com.dss.copilot.rent.model.entity.Car;
import com.dss.copilot.rent.model.entity.Reservation;
import com.dss.copilot.rent.model.entity.User;
import com.dss.copilot.rent.model.repository.CarDao;
import com.dss.copilot.rent.model.repository.ReservationDao;
import com.dss.copilot.rent.model.repository.UserDao;
import com.dss.copilot.rent.model.request.CancelRequest;
import com.dss.copilot.rent.model.request.MyBookingsRequest;
import com.dss.copilot.rent.model.request.ReserveRequest;
import com.dss.copilot.rent.model.response.Response;
import com.dss.copilot.rent.model.response.ResponseMyBookings;
import com.dss.copilot.rent.model.response.ResponseReserve;
import com.dss.copilot.rent.model.service.ReservationService;
import com.dss.copilot.rent.model.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * ReservationServiceImpl class.
 * add @Service annotation.
 * implements ReservationService interface.
 * inject ReservationDao using constructor injection.
 * inject UserDao using constructor injection.
 */
@Service
public class ReservationServiceImpl implements ReservationService {


    private final ReservationDao reservationDao;
    private final UserDao userDao;
    private final CarDao carDao;
    private final TokenService tokenService;

    public ReservationServiceImpl(ReservationDao reservationDao, UserDao userDao, CarDao carDao, TokenService tokenService) {
        this.reservationDao = reservationDao;
        this.userDao = userDao;
        this.carDao = carDao;
        this.tokenService = tokenService;
    }

    /**
     * reserve method.
     *
     * @param reserveRequest ReserveRequest object.
     * @param token          String object.
     * @return ResponseReserve object.
     */
    @Override
    public ResponseReserve reserve(ReserveRequest reserveRequest, String token) {

        // build reservation object with reserveRequest data using builder
        // call save method from reservationDao passing reservation object
        // build ReserveData object with reservation object data
        // set ReserveData object to ResponseReserve object using builder
        // return ResponseReserve object
        // validate if car is available for the given dates
        // validate if there are no overlapping reservations for the given dates
        // validate if pickup date is before return date
        // validate if pickup date and return date are today or in the future

        if (reserveRequest.getPickupDate().before(new Date()) || reserveRequest.getReturnDate().before(new Date())) {
            ResponseReserve responseReserve = ResponseReserve.builder().build();
            responseReserve.setMessage("Pickup and return dates should be today or in the future");
            responseReserve.setStatus(Constants.ERROR);
            return responseReserve;
        }

        if (reserveRequest.getPickupDate().after(reserveRequest.getReturnDate())) {
            ResponseReserve responseReserve = ResponseReserve.builder().build();
            responseReserve.setMessage("Return date should be after pickup date");
            responseReserve.setStatus(Constants.ERROR);
            return responseReserve;
        }

        String userEmail = tokenService.getSubjectFromToken(token);

        User user = userDao.findByUserEmail(userEmail).orElseThrow();

        Car car = carDao.findById(reserveRequest.getCarId()).orElseThrow();

        List<Reservation> reservations = reservationDao.findByCarIdAndPickupDateGreaterThanEqualAndReturnDateLessThanEqual(reserveRequest.getCarId(), reserveRequest.getPickupDate(), reserveRequest.getReturnDate());

        if (!reservations.isEmpty()) {
            ResponseReserve responseReserve = ResponseReserve.builder().build();
            responseReserve.setMessage("Car is not available for the given dates");
            responseReserve.setStatus(Constants.ERROR);
            return responseReserve;
        }

        Reservation reservation = Reservation.builder()
                .numOfTravellers(reserveRequest.getNumOfTravellers())
                .reservationDate(new Date())
                .pickupDate(reserveRequest.getPickupDate())
                .returnDate(reserveRequest.getReturnDate())
                .carId(reserveRequest.getCarId())
                .car(car.getBrand().concat(" ").concat(car.getCarModel()))
                .img(car.getThumbnail())
                .status("CONFIRMED")
                .total(car.getPricePerHour() * reserveRequest.getNumOfTravellers())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .build();

        reservation = reservationDao.save(reservation);

        ReserveData reservationData = ReserveData.builder()
                .bookingId(reservation.getBookingId())
                .userEmail(reservation.getUserEmail())
                .build();

        ResponseReserve responseReserve = ResponseReserve.builder().data(reservationData).build();
        responseReserve.setMessage("Reservation successful");
        responseReserve.setStatus(Constants.SUCCESS);

        return responseReserve;

    }

    /**
     * cancel method.
     *
     * @param cancelRequest CancelRequest object.
     * @return Response object.
     */
    @Override
    public Response cancel(CancelRequest cancelRequest) {
        // find reservation by bookingId using reservationDao
        // validate if reservation is present
        // set status to CANCELLED
        // call save method from reservationDao passing reservation object
        // build Response object
        // return Response object

        Reservation reservation = reservationDao.findById(cancelRequest.getBookingId()).orElseThrow();

        reservation.setStatus("CANCELLED");

        reservationDao.save(reservation);

        Response response = new Response();
        response.setStatus(Constants.SUCCESS);
        response.setMessage("Your reservation is cancelled.");
        return response;

    }

    /**
     * getMyBookings method.
     *
     * @param myBookingsRequest MyBookingsRequest object.
     * @return ResponseMyBookings object.
     */
    @Override
    public ResponseMyBookings getMyBookings(MyBookingsRequest myBookingsRequest) {
        // call findByUserEmail method from reservationDao passing myBookingsRequest object
        // validate if reservations list is empty
        // build ResponseMyBookings object with reservations data
        // return ResponseMyBookings object

        List<Reservation> reservations = reservationDao.findByUserEmail(myBookingsRequest.getUserEmail());

        if (reservations.isEmpty()) {
            ResponseMyBookings response = new ResponseMyBookings();
            response.setStatus("success");
            response.setMessage("No reservations found");
            return response;
        }

        List<MyBookingData> booKingsData = reservations.stream()
                .map(reservation -> MyBookingData.builder()
                        .bookingId(reservation.getBookingId())
                        .userEmail(reservation.getUserEmail())
                        .carId(reservation.getCarId())
                        .reservationDate(reservation.getReservationDate().toString())
                        .pickupDate(reservation.getPickupDate().toString())
                        .returnDate(reservation.getReturnDate().toString())
                        .numOfTravellers(reservation.getNumOfTravellers())
                        .status(reservation.getStatus())
                        .car(reservation.getCar())
                        .img(reservation.getImg())
                        .total(reservation.getTotal())
                        .build())
                .collect(Collectors.toList());

        MyBookingsData myBookingsData = MyBookingsData.builder().bookings(booKingsData).build();
        ResponseMyBookings response = new ResponseMyBookings();
        response.setStatus("success");
        response.setData(myBookingsData);
        return response;
    }
}
