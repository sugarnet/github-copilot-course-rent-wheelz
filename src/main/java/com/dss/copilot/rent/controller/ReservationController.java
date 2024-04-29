package com.dss.copilot.rent.controller;

import com.dss.copilot.rent.constant.Constants;
import com.dss.copilot.rent.model.request.CancelRequest;
import com.dss.copilot.rent.model.request.MyBookingsRequest;
import com.dss.copilot.rent.model.request.ReserveRequest;
import com.dss.copilot.rent.model.response.Response;
import com.dss.copilot.rent.model.response.ResponseMyBookings;
import com.dss.copilot.rent.model.response.ResponseReserve;
import com.dss.copilot.rent.model.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReservationController class.
 * add @RestController annotation
 * add method: reserve with @PostMapping("/reserve") annotation and @RequestBody annotation for ReservationRequest object. Return ResponseEntity with ReservationResponse object.
 * inject ReservationService using constructor injection.
 * add method: cancel with @PostMapping("/cancel") annotation and @RequestBody annotation for ReservationCancelRequest object. Return ResponseEntity with ReservationCancelResponse object.
 * add method: getMyBookings with @GetMapping("/my-bookings") annotation. Return ResponseEntity with MyBookingsResponse object.
 * add @CrossOrigin annotation. add origin with value "*".
 */
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * reserve method.
     * @param reserveRequest ReservationRequest object.
     * @return ResponseEntity with ReservationResponse object.
     */
    @PostMapping("/reserve")
    public ResponseEntity<ResponseReserve> reserve(@Valid @RequestBody ReserveRequest reserveRequest,
                                                   BindingResult bindingResult,
                                                   @RequestHeader(name = "Authorization") String token) {

        if (bindingResult.hasErrors()) {
            ResponseReserve reserveResponse = new ResponseReserve();
            reserveResponse.setStatus(Constants.ERROR);
            reserveResponse.setMessage(Constants.INVALID_INPUT);
            return new ResponseEntity<>(reserveResponse, HttpStatus.BAD_REQUEST);
        }
        ResponseReserve reserve = reservationService.reserve(reserveRequest, token);
        return new ResponseEntity<>(reserve, reserve.getStatus().equals(Constants.ERROR) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    /**
     * cancel method.
     * @param reservationCancelRequest ReservationCancelRequest object.
     * @return ResponseEntity with ReservationCancelResponse object.
     */
    @PostMapping("/cancel")
    public ResponseEntity<Response> cancel(@Valid @RequestBody CancelRequest reservationCancelRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Response cancelResponse = new Response();
            cancelResponse.setStatus(Constants.ERROR);
            cancelResponse.setMessage(Constants.INVALID_INPUT);
            return new ResponseEntity<>(cancelResponse, HttpStatus.BAD_REQUEST);
        }
        Response cancel = reservationService.cancel(reservationCancelRequest);
        return new ResponseEntity<>(cancel, cancel.getStatus().equals("error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    /**
     * getMyBookings method.
     * @return ResponseEntity with MyBookingsResponse object.
     */
    @GetMapping("/my-bookings")
    public ResponseEntity<ResponseMyBookings> getMyBookings(@Valid MyBookingsRequest myBookingsRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ResponseMyBookings response = new ResponseMyBookings();
            response.setStatus(Constants.ERROR);
            response.setMessage(Constants.INVALID_INPUT);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        ResponseMyBookings myBookings = reservationService.getMyBookings(myBookingsRequest);
        return new ResponseEntity<>(myBookings, myBookings.getStatus().equals("error") ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
