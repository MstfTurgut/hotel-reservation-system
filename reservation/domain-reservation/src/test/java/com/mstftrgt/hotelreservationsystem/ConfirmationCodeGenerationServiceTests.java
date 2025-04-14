package com.mstftrgt.hotelreservationsystem;

import com.mstftrgt.hotelreservationsystem.reservation.service.ConfirmationCodeGenerationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfirmationCodeGenerationServiceTests {

    private ConfirmationCodeGenerationService service;
    private static final int EXPECTED_LENGTH = 8;
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @BeforeEach
    public void setUp() {
        service = new ConfirmationCodeGenerationService();
    }

    @Test
    public void testGeneratedCodeIsNotNull() {
        String code = service.generateConfirmationCode();
        assertNotNull(code, "Generated confirmation code should not be null");
    }

    @Test
    public void testGeneratedCodeHasCorrectLength() {
        String code = service.generateConfirmationCode();
        assertEquals(EXPECTED_LENGTH, code.length(), "Code should be 8 characters long");
    }

    @Test
    public void testGeneratedCodeContainsOnlyAllowedCharacters() {
        String code = service.generateConfirmationCode();
        for (char c : code.toCharArray()) {
            assertTrue(ALLOWED_CHARACTERS.indexOf(c) >= 0,
                    "Code contains invalid character: " + c);
        }
    }

    @Test
    public void testGeneratedCodesAreRandom() {
        String code1 = service.generateConfirmationCode();
        String code2 = service.generateConfirmationCode();

        assertNotEquals(code1, code2, "Generated codes should be different");
    }

    @Test
    public void testMultipleGeneratedCodesAreValid() {
        for (int i = 0; i < 100; i++) {
            String code = service.generateConfirmationCode();
            assertNotNull(code);
            assertEquals(EXPECTED_LENGTH, code.length());
            assertTrue(code.matches("[A-Z0-9]+"), "Code should match A-Z and 0-9 only");
        }
    }
}