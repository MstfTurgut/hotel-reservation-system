package com.mstftrgt.hotelreservationsystem.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBusImpl;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBusImpl;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateOnlineReservationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("controllerIntegrationTest")
@Import({CommandBusImpl.class, QueryBusImpl.class})
class ReservationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private QueryBus queryBus;

    private ObjectMapper objectMapper;

    private final static UUID RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID = UUID.fromString("99999999-9999-9999-9999-999999999999");

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void shouldCreateReservationSuccessfully() throws Exception {
        CreateOnlineReservationRequest request = new CreateOnlineReservationRequest(
                UUID.randomUUID(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2,
                0,
                "John Doe",
                "123456789",
                "john.doe@example.com",
                BigDecimal.valueOf(300),
                new CardDetails("4111111111111111", "John Doe", "12/30", "123")
        );

        mockMvc.perform(post("/api/reservations/create-online")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnErrorWhenRoomIsNotAvailable() throws Exception {
        CreateOnlineReservationRequest request = new CreateOnlineReservationRequest(
                RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2,
                0,
                "John Doe",
                "123456789",
                "john.doe@example.com",
                BigDecimal.valueOf(300),
                new CardDetails("4111111111111111", "John Doe", "12/30", "123")
        );

        mockMvc.perform(post("/api/reservations/create-online")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    void shouldReturnErrorWhenCreateOnlineReservationRequestIsNotValid() throws Exception {
        CreateOnlineReservationRequest request = new CreateOnlineReservationRequest(
                null,
                null,
                null,
                -5,
                -1000,
                "",
                "",
                "",
                BigDecimal.valueOf(-300),
                null
        );

        mockMvc.perform(post("/api/reservations/create-online")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


}