package com.mstftrgt.hotelreservationsystem.queryhandler;

import com.mstftrgt.hotelreservationsystem.ApplicationTestDataFactory;
import com.mstftrgt.hotelreservationsystem.IdentityManagementFacade;
import com.mstftrgt.hotelreservationsystem.UserContract;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforuser.FindReservationsOfUserQuery;
import com.mstftrgt.hotelreservationsystem.query.reservation.findforuser.FindReservationsOfUserQueryHandler;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindReservationsOfUserTests {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private IdentityManagementFacade identityManagementFacade;

    @InjectMocks
    private FindReservationsOfUserQueryHandler handler;

    @Test
    void shouldReturnReservationReadModelsWhenReservationsExist() {
        FindReservationsOfUserQuery query = ApplicationTestDataFactory.getFindReservationsOfUserTestQuery();

        List<Reservation> testReservationList = ApplicationTestDataFactory.getTestReservationList();

        List<ReservationReadModel> expectedReadModels = testReservationList.stream()
                .map(ReservationReadModel::from)
                .toList();

        when(identityManagementFacade.getCurrentUser())
                .thenReturn(new UserContract(UUID.randomUUID()));
        when(reservationRepository.findAllByUserId(any(UUID.class)))
                .thenReturn(testReservationList);

        List<ReservationReadModel> result = handler.handle(query);

        assertEquals(expectedReadModels, result);
    }

    @Test
    void shouldReturnEmptyListWhenNoReservationsExist() {
        FindReservationsOfUserQuery query = ApplicationTestDataFactory.getFindReservationsOfUserTestQuery();

        when(identityManagementFacade.getCurrentUser())
                .thenReturn(new UserContract(UUID.randomUUID()));
        when(reservationRepository.findAllByUserId(any(UUID.class)))
                .thenReturn(Collections.emptyList());

        List<ReservationReadModel> result = handler.handle(query);

        assertEquals(Collections.emptyList(), result);
    }
}