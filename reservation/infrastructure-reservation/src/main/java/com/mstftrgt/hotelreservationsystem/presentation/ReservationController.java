package com.mstftrgt.hotelreservationsystem.presentation;

import com.mstftrgt.hotelreservationsystem.command.reservation.checkout.CheckOutReservationCommand;
import com.mstftrgt.hotelreservationsystem.config.UserContract;
import com.mstftrgt.hotelreservationsystem.cqrs.CommandBus;
import com.mstftrgt.hotelreservationsystem.cqrs.QueryBus;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CancelReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CheckInReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.FindReservationAvailabilitiesForSuitableRoomTypesRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.FindReservationsOfCustomerRequest;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforuser.FindReservationsOfUserQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationAvailabilityForRoomTypeReadModel;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PutMapping("{reservationId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelReservation(@PathVariable UUID reservationId, @RequestBody CancelReservationRequest request) {
        commandBus.dispatch(request.toCommand(reservationId));
    }

    @PutMapping("{reservationId}/check-in")
    @ResponseStatus(HttpStatus.OK)
    public void checkInReservation(@PathVariable UUID reservationId, @RequestBody CheckInReservationRequest request) {
        commandBus.dispatch(request.toCommand(reservationId));
    }

    @PutMapping("{reservationId}/check-out")
    @ResponseStatus(HttpStatus.OK)
    public void checkOutReservation(@PathVariable UUID reservationId) {
        commandBus.dispatch(new CheckOutReservationCommand(reservationId));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@RequestBody CreateReservationRequest request) {
        UserContract currentUser = (UserContract) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        commandBus.dispatch(request.toCommand(currentUser.id()));
    }

    @GetMapping("availability")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationAvailabilityForRoomTypeReadModel> findReservationAvailabilitiesForSuitableRoomTypes(@RequestBody FindReservationAvailabilitiesForSuitableRoomTypesRequest request) {
        return queryBus.dispatch(request.toQuery());
    }

    @GetMapping("user")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationReadModel> findReservationsOfUser() {
        UserContract currentUser = (UserContract) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return queryBus.dispatch(new FindReservationsOfUserQuery(currentUser.id()));
    }

    @GetMapping("customer")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationReadModel> findReservationsOfCustomer(@RequestBody FindReservationsOfCustomerRequest request) {
        return queryBus.dispatch(request.toQuery());
    }
}
