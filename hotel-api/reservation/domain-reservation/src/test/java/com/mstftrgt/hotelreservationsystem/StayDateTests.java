package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.reservation.model.StayDate;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StayDateTests {


    @Test
    void overlaps_whenStayDatesOverlap_shouldReturnTrue() {
        StayDate stayDate1 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 5))
                .build();

        StayDate stayDate2 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 3))
                .checkOutDate(LocalDate.of(2030, 1, 7))
                .build();

        assertTrue(stayDate1.overlaps(stayDate2));
        assertTrue(stayDate2.overlaps(stayDate1));
    }

    @Test
    void overlaps_whenStayDatesBorderline_shouldReturnTrue() {
        StayDate stayDate1 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 5))
                .build();

        StayDate stayDate2 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 5))
                .checkOutDate(LocalDate.of(2030, 1, 7))
                .build();

        assertTrue(stayDate1.overlaps(stayDate2));
        assertTrue(stayDate2.overlaps(stayDate1));
    }

    @Test
    void overlaps_whenStayDatesDoNotOverlap_shouldReturnFalse() {
        StayDate stayDate1 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 3))
                .build();

        StayDate stayDate2 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 4))
                .checkOutDate(LocalDate.of(2030, 1, 7))
                .build();

        assertFalse(stayDate1.overlaps(stayDate2));
        assertFalse(stayDate2.overlaps(stayDate1));
    }

    @Test
    void overlaps_whenOneStayDateContainsAnother_shouldReturnTrue() {
        StayDate stayDate1 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 10))
                .build();

        StayDate stayDate2 = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 3))
                .checkOutDate(LocalDate.of(2030, 1, 7))
                .build();

        assertTrue(stayDate1.overlaps(stayDate2));
        assertTrue(stayDate2.overlaps(stayDate1));
    }

    @Test
    void isInverse_whenCheckInAfterCheckOut_shouldReturnTrue() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 5))
                .checkOutDate(LocalDate.of(2030, 1, 3))
                .build();

        assertTrue(stayDate.isInverse());
    }

    @Test
    void isInverse_whenCheckInBeforeCheckOut_shouldReturnFalse() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 3))
                .checkOutDate(LocalDate.of(2030, 1, 5))
                .build();

        assertFalse(stayDate.isInverse());
    }

    @Test
    void isPast_whenCheckInDateInPast_shouldReturnTrue() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.of(1900, 1, 1))
                .checkOutDate(LocalDate.of(1900, 1, 3))
                .build();

        assertTrue(stayDate.isPast());
    }

    @Test
    void isPast_whenCheckInDateIsToday_shouldReturnFalse() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(3))
                .build();

        assertFalse(stayDate.isPast());
    }

    @Test
    void isPast_whenCheckInDateInFuture_shouldReturnFalse() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.now().plusDays(5))
                .checkOutDate(LocalDate.now().plusDays(10))
                .build();

        assertFalse(stayDate.isPast());
    }

    @Test
    void hasInvalidLength_whenStayDateHasAtLeastLengthOfOne_shouldReturnFalse() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 3))
                .build();

        assertFalse(stayDate.hasInvalidLength());
    }

    @Test
    void hasInvalidLength_whenStayDateDoesNotHaveAtLeastALengthOfOne_shouldReturnTrue() {
        StayDate stayDate = StayDate.builder()
                .checkInDate(LocalDate.of(2030, 1, 1))
                .checkOutDate(LocalDate.of(2030, 1, 1))
                .build();

        assertTrue(stayDate.hasInvalidLength());
    }
}