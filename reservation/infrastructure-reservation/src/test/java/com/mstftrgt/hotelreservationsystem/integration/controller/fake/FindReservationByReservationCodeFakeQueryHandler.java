package com.mstftrgt.hotelreservationsystem.integration.controller.fake;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.query.reservation.findbyreservationcode.FindReservationByReservationCodeQuery;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_RESERVATION_CODE;

@Service
public class FindReservationByReservationCodeFakeQueryHandler implements QueryHandler<FindReservationByReservationCodeQuery, ReservationReadModel> {

    @Override
    public ReservationReadModel handle(FindReservationByReservationCodeQuery query) {

        if(RESERVATION_NOT_FOUND_RESERVATION_CODE.equals(query.reservationCode())) {
            throw new ReservationNotFoundException(query.reservationCode());
        }

        return ReservationReadModel
                .builder()
                .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .userId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .roomId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .reservationCode("11111111")
                .adultGuestCount(2)
                .childGuestCount(1)
                .status(ReservationStatus.CONFIRMED.toString())
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 1))
                .customerFullName("John Doe")
                .customerPhoneNumber("0123456789")
                .customerEmailAddress("johndoe@gmail.com")
                .build();
    }
}
