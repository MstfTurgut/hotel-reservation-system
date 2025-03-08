package com.mstftrgt.hotelreservationsystem.query.reservation.findforcustomer;

import com.mstftrgt.hotelreservationsystem.QueryHandler;
import com.mstftrgt.hotelreservationsystem.readmodel.ReservationQueryReadModel;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindReservationsOfCustomerQueryHandler implements QueryHandler<FindReservationsOfCustomerQuery, List<ReservationQueryReadModel>> {

    private final ReservationRepository reservationRepository;


    @Override
    public List<ReservationQueryReadModel> handle(FindReservationsOfCustomerQuery query) {
        return reservationRepository
                .findAllByCustomerFullNameAndPhoneNumber(query.fullName(), query.phoneNumber())
                .stream()
                .map(ReservationQueryReadModel::from)
                .toList();
    }
}
