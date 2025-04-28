package com.mstftrgt.hotelreservationsystem.persistence;

import com.mstftrgt.hotelreservationsystem.persistence.entity.ReservationEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.ReservationJpaRepository;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationDataAdapter implements ReservationRepository {

    private final ApplicationEventPublisher publisher;
    private final ReservationJpaRepository reservationJpaRepository;

    @Override
    public void save(Reservation reservation) {
        reservationJpaRepository.save(ReservationEntity.from(reservation));

        reservation.getDomainEvents().forEach(publisher::publishEvent);
        reservation.clearEvents();
    }

    @Override
    public void delete(Reservation reservation) {
        reservationJpaRepository.deleteById(reservation.getId());

        reservation.getDomainEvents().forEach(publisher::publishEvent);
        reservation.clearEvents();
    }

    @Override
    public Optional<Reservation> findById(UUID reservationId) {
        return reservationJpaRepository.findById(reservationId)
                .map(ReservationEntity::toModel);
    }

    @Override
    public List<Reservation> findReservationsOfRoom(UUID roomId) {
        return reservationJpaRepository.findAllByRoomId(roomId)
                .stream()
                .map(ReservationEntity::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findAllByCustomerFullNameAndPhoneNumber(String fullName, String phoneNumber) {
        return reservationJpaRepository.findAllByFullNameAndPhoneNumber(fullName, phoneNumber)
                .stream()
                .map(ReservationEntity::toModel)
                .toList();
    }

    @Override
    public List<Reservation> findAllByUserId(UUID userId) {
        return reservationJpaRepository.findAllByUserId(userId)
                .stream()
                .map(ReservationEntity::toModel)
                .toList();
    }
}
