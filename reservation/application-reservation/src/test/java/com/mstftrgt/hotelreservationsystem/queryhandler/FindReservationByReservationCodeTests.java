package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.IdentityManagementFacade;
import com.mstftrgt.hotelreservationsystem.query.reservation.findbyreservationcode.FindReservationByReservationCodeQuery;
import com.mstftrgt.hotelreservationsystem.query.reservation.findbyreservationcode.FindReservationByReservationCodeQueryHandler;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforuser.FindReservationsOfUserQueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FindReservationByReservationCodeTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private FindReservationByReservationCodeQueryHandler handler;

    @Test
    void shouldReturnPaymentReadModelWhenPaymentExists() {
        FindReservationByReservationCodeQuery query = ApplicationTestDataFactory.getFindReservationByReservationCodeTestQuery();

        Reservation reservation = ApplicationTestDataFactory.getTestReservation();
        ReservationReadModel expectedReadModel = ReservationReadModel.from(reservation);

        when(reservationRepository.findByReservationCode(query.reservationCode())).thenReturn(Optional.of(reservation));

        ReservationReadModel result = handler.handle(query);

        assertEquals(expectedReadModel, result);
    }

    @Test
    void shouldThrowPaymentNotFoundExceptionWhenPaymentDoesNotExist() {
        FindReservationByReservationCodeQuery query = ApplicationTestDataFactory.getFindReservationByReservationCodeTestQuery();

        when(reservationRepository.findByReservationCode(query.reservationCode())).thenReturn(Optional.empty());

        assertThrows(ReservationNotFoundException.class, () -> handler.handle(query));
    }
}
