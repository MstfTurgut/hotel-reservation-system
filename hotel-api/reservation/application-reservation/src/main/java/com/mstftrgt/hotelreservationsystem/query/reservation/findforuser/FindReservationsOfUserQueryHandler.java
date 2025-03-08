package com.mstftrgt.hotelreservationsystem.query.reservation.findforuser;

import com.mstftrgt.hotelreservationsystem.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationQueryReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindReservationsOfUserQueryHandler implements QueryHandler<FindReservationsOfUserQuery, List<ReservationQueryReadModel>> {

    private final ReservationRepository reservationRepository;

    @Override
    public List<ReservationQueryReadModel> handle(FindReservationsOfUserQuery query) {
        return reservationRepository
                .findAllByUserId(query.userId())
                .stream()
                .map(ReservationQueryReadModel::from)
                .toList();
    }
}
