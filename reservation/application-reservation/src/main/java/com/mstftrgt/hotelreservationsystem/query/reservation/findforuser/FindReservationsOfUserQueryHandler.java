package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.cqrs.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindReservationsOfUserQueryHandler implements QueryHandler<FindReservationsOfUserQuery, List<ReservationReadModel>> {

    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationReadModel> handle(FindReservationsOfUserQuery query) {
        return reservationRepository
                .findAllByUserId(query.userId())
                .stream()
                .map(ReservationReadModel::from)
                .toList();
    }
}
