package com.dss.copilot.rent.model.service;

import com.dss.copilot.rent.model.request.CancelRequest;
import com.dss.copilot.rent.model.request.MyBookingsRequest;
import com.dss.copilot.rent.model.request.ReserveRequest;
import com.dss.copilot.rent.model.response.Response;
import com.dss.copilot.rent.model.response.ResponseMyBookings;
import com.dss.copilot.rent.model.response.ResponseReserve;

/**
 * ReservationsService interface.
 * add methods: reserve, cancel and getMyBookings.
 */
public interface ReservationService {
    ResponseReserve reserve(ReserveRequest reserveRequest, String token);
    Response cancel(CancelRequest cancelRequest);
    ResponseMyBookings getMyBookings(MyBookingsRequest myBookingsRequest);
}
