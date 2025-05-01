package com.mstftrgt.hotelreservationsystem.integration.dataadapter;

import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.persistence.ReservationDataAdapter;
import com.mstftrgt.hotelreservationsystem.persistence.entity.ReservationEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.ReservationJpaRepository;
import com.mstftrgt.hotelreservationsystem.reservation.dto.ReservationCreate;
import com.mstftrgt.hotelreservationsystem.reservation.event.ReservationCreatedDomainEvent;
import com.mstftrgt.hotelreservationsystem.reservation.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("dataAdapterIntegrationTest")
class ReservationDataAdapterIT {

    @MockitoBean
    private ApplicationEventPublisher publisher;

    private ReservationDataAdapter reservationDataAdapter;

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @BeforeEach
    void setUp() {
        this.reservationDataAdapter = new ReservationDataAdapter(publisher, reservationJpaRepository);
    }

    @Test
    void save_shouldPersistReservation() {
        ReservationCreate reservationCreate = ReservationCreate.builder()
                .userId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .roomId(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .adultGuestCount(1)
                .childGuestCount(1)
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 3))
                .customerFullName("testFullName")
                .customerEmailAddress("test@example.com")
                .customerPhoneNumber("1234567890")
                .confirmationCode("testConfirmationCode")
                .reservationCode("testReservationCode")
                .totalPrice(BigDecimal.TEN)
                .cardDetails(new CardDetails("testCardNumber", "testHolderName", "testExpiryDate", "testCvv"))
                .build();

        Reservation reservation = Reservation.create(reservationCreate);

        reservationDataAdapter.save(reservation);

        Optional<ReservationEntity> result = reservationJpaRepository.findById(reservation.getId());

        assertTrue(result.isPresent());

        verify(publisher, times(1)).publishEvent(any(ReservationCreatedDomainEvent.class));
    }

    @Test
    void delete_shouldDeleteReservation() {
        Optional<Reservation> reservation = reservationJpaRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111")).map(ReservationEntity::toModel);
        assertTrue(reservation.isPresent(), "Reservation should be present before deletion");

        reservationDataAdapter.delete(reservation.get());

        Optional<ReservationEntity> result = reservationJpaRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_shouldRetrieveReservation() {
        Optional<Reservation> optionalReservation = reservationDataAdapter.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        assertTrue(optionalReservation.isPresent());

        Reservation reservation = optionalReservation.get();
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getUserId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getRoomId());
        assertEquals("12345678", reservation.getConfirmationCode());
        assertEquals("12345678", reservation.getReservationCode());
        assertEquals(2, reservation.getGuestSpecification().getAdultGuestCount());
        assertEquals(1, reservation.getGuestSpecification().getChildGuestCount());
        assertEquals("CONFIRMED", reservation.getStatus().name()); // assuming status is an enum
        assertEquals(LocalDate.of(2030, 1, 1), reservation.getStayDate().getCheckInDate());
        assertEquals(LocalDate.of(2030, 1, 5), reservation.getStayDate().getCheckOutDate());
        assertEquals("John Doe", reservation.getCustomerDetails().getFullName());
        assertEquals("1234567890", reservation.getCustomerDetails().getPhoneNumber());
        assertEquals("johndoe@example.com", reservation.getCustomerDetails().getEmailAddress());
        assertNotNull(reservation.getCreatedAt());
    }

    @Test
    void findReservationsOfRoom_shouldRetrieveReservations() {
        List<Reservation> reservationsOfRoom = reservationDataAdapter.findReservationsOfRoom(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        assertEquals(reservationsOfRoom.size(), 1);

        Reservation reservation = reservationsOfRoom.get(0);

        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getUserId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getRoomId());
        assertEquals("12345678", reservation.getConfirmationCode());
        assertEquals("12345678", reservation.getReservationCode());
        assertEquals(2, reservation.getGuestSpecification().getAdultGuestCount());
        assertEquals(1, reservation.getGuestSpecification().getChildGuestCount());
        assertEquals("CONFIRMED", reservation.getStatus().name()); // assuming status is an enum
        assertEquals(LocalDate.of(2030, 1, 1), reservation.getStayDate().getCheckInDate());
        assertEquals(LocalDate.of(2030, 1, 5), reservation.getStayDate().getCheckOutDate());
        assertEquals("John Doe", reservation.getCustomerDetails().getFullName());
        assertEquals("1234567890", reservation.getCustomerDetails().getPhoneNumber());
        assertEquals("johndoe@example.com", reservation.getCustomerDetails().getEmailAddress());
        assertNotNull(reservation.getCreatedAt());
    }

    @Test
    void findAllByCustomerFullNameAndPhoneNumber_shouldRetrieveReservations() {
        List<Reservation> reservationsOfRoom = reservationDataAdapter.findAllByCustomerFullNameAndPhoneNumber("John Doe", "1234567890");

        assertEquals(reservationsOfRoom.size(), 1);

        Reservation reservation = reservationsOfRoom.get(0);

        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getUserId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getRoomId());
        assertEquals("12345678", reservation.getConfirmationCode());
        assertEquals("12345678", reservation.getReservationCode());
        assertEquals(2, reservation.getGuestSpecification().getAdultGuestCount());
        assertEquals(1, reservation.getGuestSpecification().getChildGuestCount());
        assertEquals("CONFIRMED", reservation.getStatus().name());
        assertEquals(LocalDate.of(2030, 1, 1), reservation.getStayDate().getCheckInDate());
        assertEquals(LocalDate.of(2030, 1, 5), reservation.getStayDate().getCheckOutDate());
        assertEquals("John Doe", reservation.getCustomerDetails().getFullName());
        assertEquals("1234567890", reservation.getCustomerDetails().getPhoneNumber());
        assertEquals("johndoe@example.com", reservation.getCustomerDetails().getEmailAddress());
        assertNotNull(reservation.getCreatedAt());
    }

    @Test
    void findAllByUserId_shouldRetrieveReservations() {
        List<Reservation> reservationsOfRoom = reservationDataAdapter.findAllByUserId(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        assertEquals(reservationsOfRoom.size(), 1);

        Reservation reservation = reservationsOfRoom.get(0);

        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getUserId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), reservation.getRoomId());
        assertEquals("12345678", reservation.getConfirmationCode());
        assertEquals("12345678", reservation.getReservationCode());
        assertEquals(2, reservation.getGuestSpecification().getAdultGuestCount());
        assertEquals(1, reservation.getGuestSpecification().getChildGuestCount());
        assertEquals("CONFIRMED", reservation.getStatus().name()); // assuming status is an enum
        assertEquals(LocalDate.of(2030, 1, 1), reservation.getStayDate().getCheckInDate());
        assertEquals(LocalDate.of(2030, 1, 5), reservation.getStayDate().getCheckOutDate());
        assertEquals("John Doe", reservation.getCustomerDetails().getFullName());
        assertEquals("1234567890", reservation.getCustomerDetails().getPhoneNumber());
        assertEquals("johndoe@example.com", reservation.getCustomerDetails().getEmailAddress());
        assertNotNull(reservation.getCreatedAt());
    }
}