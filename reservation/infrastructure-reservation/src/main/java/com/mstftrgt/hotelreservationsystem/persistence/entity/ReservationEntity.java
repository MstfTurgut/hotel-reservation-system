package com.mstftrgt.hotelreservationsystem.persistence.entity;


import com.mstftrgt.hotelreservationsystem.reservation.model.CustomerDetails;
import com.mstftrgt.hotelreservationsystem.reservation.model.GuestSpecification;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import com.mstftrgt.hotelreservationsystem.reservation.model.ReservationStatus;
import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation", schema = "reservation")
public class ReservationEntity {

    @Id
    private UUID id;
    private UUID userId;
    private UUID roomId;
    private String confirmationCode;
    private String reservationCode;
    private int adultGuestCount;
    private int childGuestCount;
    private String status;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String fullName;
    private String phoneNumber;
    private String emailAddress;
    private LocalDateTime createdAt;

    public static ReservationEntity from(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.getId())
                .userId(reservation.getUserId())
                .roomId(reservation.getRoomId())
                .confirmationCode(reservation.getConfirmationCode())
                .reservationCode(reservation.getReservationCode())
                .adultGuestCount(reservation.getGuestSpecification().getAdultGuestCount())
                .childGuestCount(reservation.getGuestSpecification().getChildGuestCount())
                .status(reservation.getStatus().name())
                .checkInDate(reservation.getStayDate().getCheckInDate())
                .checkOutDate(reservation.getStayDate().getCheckOutDate())
                .fullName(reservation.getCustomerDetails().getFullName())
                .phoneNumber(reservation.getCustomerDetails().getPhoneNumber())
                .emailAddress(reservation.getCustomerDetails().getEmailAddress())
                .createdAt(reservation.getCreatedAt())
                .build();
    }

    public Reservation toModel() {
        GuestSpecification guestSpec = new GuestSpecification(
                this.adultGuestCount,
                this.childGuestCount
        );

        StayDate stayDate = new StayDate(
                this.checkInDate,
                this.checkOutDate
        );

        CustomerDetails customerDetails = new CustomerDetails(
                this.fullName,
                this.phoneNumber,
                this.emailAddress
        );

        return new Reservation(
                this.id,
                this.userId,
                this.roomId,
                this.confirmationCode,
                this.reservationCode,
                guestSpec,
                ReservationStatus.valueOf(this.status),
                stayDate,
                customerDetails,
                this.createdAt
        );
    }
}
