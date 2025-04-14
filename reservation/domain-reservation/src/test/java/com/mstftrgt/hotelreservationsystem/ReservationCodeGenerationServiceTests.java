package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.reservation.service.ReservationCodeGenerationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReservationCodeGenerationServiceTests {

    private ReservationCodeGenerationService reservationCodeGenerationService;

    @BeforeEach
    public void setUp() {
        reservationCodeGenerationService = new ReservationCodeGenerationService();
    }

    @Test
    public void testGeneratedCodeIsNotNull() {
        String code = reservationCodeGenerationService.generateReservationCode();
        assertNotNull(code, "Generated code should not be null");
    }

    @Test
    public void testGeneratedCodeHasCorrectLength() {
        String code = reservationCodeGenerationService.generateReservationCode();
        assertEquals(8, code.length(), "Generated code should have length 8");
    }

    @Test
    public void testGeneratedCodeIsNumeric() {
        String code = reservationCodeGenerationService.generateReservationCode();
        assertTrue(code.matches("\\d+"), "Generated code should contain only digits");
    }

    @Test
    public void testGeneratedCodesAreRandom() {
        String code1 = reservationCodeGenerationService.generateReservationCode();
        String code2 = reservationCodeGenerationService.generateReservationCode();

        assertNotEquals(code1, code2, "Generated codes should be different");
    }

    @Test
    public void testMultipleGeneratedCodesAreValid() {
        for (int i = 0; i < 100; i++) {
            String code = reservationCodeGenerationService.generateReservationCode();
            assertNotNull(code);
            assertEquals(8, code.length());
            assertTrue(code.matches("\\d+"));
        }
    }
}