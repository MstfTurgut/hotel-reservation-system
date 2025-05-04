package com.mstftrgt.hotelreservationsystem.query.reservation.findbyreservationcode;

import com.mstftrgt.hotelreservationsystem.generic.application.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.exception.ReservationNotFoundException;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindReservationByReservationCodeQueryHandler implements QueryHandler<FindReservationByReservationCodeQuery, ReservationReadModel> {

    private final ReservationRepository reservationRepository;

    @Override
    public ReservationReadModel handle(FindReservationByReservationCodeQuery query) {
        return reservationRepository.findByReservationCode(query.reservationCode())
                .map(ReservationReadModel::from)
                .orElseThrow(() -> new ReservationNotFoundException(query.reservationCode()));
    }
}
