package com.mstftrgt.hotelreservationsystem.presentation;

import com.mstftrgt.hotelreservationsystem.command.reservation.checkout.CheckOutReservationCommand;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CancelReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CheckInReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateInHotelReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateOnlineReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.FindReservationAvailabilitiesForSuitableRoomTypesRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.FindReservationsOfCustomerRequest;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforuser.FindReservationsOfUserQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping("/create-online")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createOnlineReservation(@Valid @RequestBody CreateOnlineReservationRequest request) {
        return commandBus.dispatchAndReturn(request.toCommand());
    }

    @PostMapping("/create-in-hotel")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createInHotelReservation(@Valid @RequestBody CreateInHotelReservationRequest request) {
        return commandBus.dispatchAndReturn(request.toCommand());
    }

    @PutMapping("{reservationId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelReservation(@PathVariable UUID reservationId, @Valid @RequestBody CancelReservationRequest request) {
        commandBus.dispatch(request.toCommand(reservationId));
    }

    @PutMapping("{reservationId}/check-in")
    @ResponseStatus(HttpStatus.OK)
    public void checkInReservation(@PathVariable UUID reservationId, @Valid @RequestBody CheckInReservationRequest request) {
        commandBus.dispatch(request.toCommand(reservationId));
    }

    @PutMapping("{reservationId}/check-out")
    @ResponseStatus(HttpStatus.OK)
    public void checkOutReservation(@PathVariable UUID reservationId) {
        commandBus.dispatch(new CheckOutReservationCommand(reservationId));
    }

    @GetMapping("availability")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationAvailabilityForRoomTypeReadModel> findReservationAvailabilitiesForSuitableRoomTypes(@Valid @RequestBody FindReservationAvailabilitiesForSuitableRoomTypesRequest request) {
        return queryBus.dispatchAndReturn(request.toQuery());
    }

    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationReadModel> findReservationsOfUser() {
        return queryBus.dispatchAndReturn(new FindReservationsOfUserQuery());
    }

    @GetMapping("customer")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationReadModel> findReservationsOfCustomer(@Valid @RequestBody FindReservationsOfCustomerRequest request) {
        return queryBus.dispatchAndReturn(request.toQuery());
    }
}
