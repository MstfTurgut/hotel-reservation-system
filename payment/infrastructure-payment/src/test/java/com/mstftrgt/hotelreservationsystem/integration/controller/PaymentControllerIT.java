package com.mstftrgt.hotelreservationsystem.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBusImpl;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBusImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.PAYMENT_NOT_FOUND_RESERVATION_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("controllerIntegrationTest")
@Import({CommandBusImpl.class, QueryBusImpl.class})
public class PaymentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private QueryBus queryBus;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void findPaymentForReservation_shouldReturnPayment() throws Exception {
        UUID reservationId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        mockMvc.perform(get("/api/payments/reservation/" + reservationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$.transactionId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$.reservationId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$.amount").value(10))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }


    @Test
    void findReservationAvailabilities_withInvalidRequest_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/payments/reservation/" + PAYMENT_NOT_FOUND_RESERVATION_ID))
                .andExpect(status().isNotFound());
    }
}
