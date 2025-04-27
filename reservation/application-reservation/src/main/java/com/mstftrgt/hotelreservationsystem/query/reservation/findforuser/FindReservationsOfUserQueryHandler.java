package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.IdentityManagementFacade;
import com.mstftrgt.hotelreservationsystem.UserContract;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindReservationsOfUserQueryHandler implements QueryHandler<FindReservationsOfUserQuery, List<ReservationReadModel>> {

    private final ReservationRepository reservationRepository;
    private final IdentityManagementFacade identityManagementFacade;

    @Override
    public List<ReservationReadModel> handle(FindReservationsOfUserQuery query) {
        UserContract currentUser = identityManagementFacade.getCurrentUser();

        return reservationRepository
                .findAllByUserId(currentUser.id())
                .stream()
                .map(ReservationReadModel::from)
                .toList();
    }
}
