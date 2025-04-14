package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer.FindReservationsOfCustomerQuery;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer.FindReservationsOfCustomerQueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReservationsOfCustomerTests {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private FindReservationsOfCustomerQueryHandler handler;

    @Test
    void shouldReturnReservationReadModelsWhenReservationsExist() {
        FindReservationsOfCustomerQuery query = ApplicationTestDataFactory.getFindReservationsOfCustomerTestQuery();

        List<Reservation> testReservationList = ApplicationTestDataFactory.getTestReservationList();

        List<ReservationReadModel> expectedReadModels = testReservationList.stream()
                .map(ReservationReadModel::from)
                .toList();

        when(reservationRepository.findAllByCustomerFullNameAndPhoneNumber(query.fullName(), query.phoneNumber()))
                .thenReturn(testReservationList);

        List<ReservationReadModel> result = handler.handle(query);

        assertEquals(expectedReadModels, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoReservationsExist() {
        FindReservationsOfCustomerQuery query = ApplicationTestDataFactory.getFindReservationsOfCustomerTestQuery();

        when(reservationRepository.findAllByCustomerFullNameAndPhoneNumber(query.fullName(), query.phoneNumber()))
                .thenReturn(Collections.emptyList());

        List<ReservationReadModel> result = handler.handle(query);

        assertEquals(Collections.emptyList(), result);
    }
}