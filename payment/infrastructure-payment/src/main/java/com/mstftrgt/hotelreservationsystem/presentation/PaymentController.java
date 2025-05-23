package com.mstftrgt.hotelreservationsystem.presentation;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.query.payment.findforreservation.FindPaymentForReservationQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.PaymentReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final QueryBus queryBus;

    @GetMapping("/reservation/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentReadModel findPaymentForReservation(@PathVariable UUID reservationId) {
        return queryBus.dispatchAndReturn(new FindPaymentForReservationQuery(reservationId));
    }
}
