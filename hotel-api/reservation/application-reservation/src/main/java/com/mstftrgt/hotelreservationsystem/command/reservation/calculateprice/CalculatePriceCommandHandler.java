package com.mstftrgt.hotelreservationsystem.command.reservation.calculateprice;


import com.mstftrgt.hotelreservationsystem.CommandHandler;
import com.mstftrgt.hotelreservationsystem.DomainEventPublisher;
import com.mstftrgt.hotelreservationsystem.facade.RoomManagementFacade;
import com.mstftrgt.hotelreservationsystem.facade.RoomTypeContract;
import com.mstftrgt.hotelreservationsystem.reservation.event.PriceCalculated;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import com.mstftrgt.hotelreservationsystem.reservation.repository.ReservationRepository;
import com.mstftrgt.hotelreservationsystem.reservation.service.PriceCalculationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculatePriceCommandHandler implements CommandHandler<CalculatePriceCommand> {

    private final DomainEventPublisher eventPublisher;
    private final RoomManagementFacade roomManagementFacade;
    private final ReservationRepository reservationRepository;
    private final PriceCalculationService priceCalculationService;

    @Override
    public void handle(CalculatePriceCommand command) {
        Reservation reservation = reservationRepository.findById(command.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        RoomTypeContract roomType = roomManagementFacade.findRoomTypeByRoomId(reservation.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        BigDecimal totalPrice = priceCalculationService.calculateTotalPrice(roomType.priceForNight(),
                new StayDate(reservation.getStayDate().getCheckInDate(), reservation.getStayDate().getCheckOutDate()));

        eventPublisher.publish(new PriceCalculated(totalPrice, command.getReservationId(), command.getCardDetails()));
    }
}
