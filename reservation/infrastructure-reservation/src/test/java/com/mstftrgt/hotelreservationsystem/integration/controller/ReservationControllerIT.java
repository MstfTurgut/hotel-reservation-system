package com.mstftrgt.hotelreservationsystem.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBusImpl;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBusImpl;
import com.mstftrgt.hotelreservationsystem.kernel.CardDetails;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CancelReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CheckInReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateInHotelReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.CreateOnlineReservationRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.FindReservationAvailabilitiesForSuitableRoomTypesRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.FindReservationsOfCustomerRequest;
import com.mstftrgt.hotelreservationsystem.query.reservation.findbyreservationcode.FindReservationByReservationCodeQuery;
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

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.INVALID_CONFIRMATION_CODE;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.INVALID_STAY_DATE_LOCAL_DATE;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_ALREADY_CANCELLED_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_ALREADY_CHECKED_IN_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_ALREADY_CHECKED_OUT_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_CHECK_IN_DATE_DOES_NOT_MATCH_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_LAST_MINUTE_CANCELLATION_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_ID;
import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.RESERVATION_NOT_FOUND_RESERVATION_CODE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void createOnlineReservation_withValidRequest_shouldReturnCreated() throws Exception {
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
    void createOnlineReservation_whenRoomNotAvailable_shouldReturnConflict() throws Exception {
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
    void createOnlineReservation_withInvalidRequest_shouldReturnBadRequest() throws Exception {
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

    @Test
    void createInHotelReservation_withValidRequest_shouldReturnCreated() throws Exception {
        CreateInHotelReservationRequest request = new CreateInHotelReservationRequest(
                UUID.randomUUID(),
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2,
                0,
                "John Doe",
                "123456789",
                "john.doe@example.com",
                BigDecimal.valueOf(300)
        );

        mockMvc.perform(post("/api/reservations/create-in-hotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void createInHotelReservation_whenRoomNotAvailable_shouldReturnConflict() throws Exception {
        CreateInHotelReservationRequest request = new CreateInHotelReservationRequest(
                RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                2,
                0,
                "John Doe",
                "123456789",
                "john.doe@example.com",
                BigDecimal.valueOf(300)
        );

        mockMvc.perform(post("/api/reservations/create-in-hotel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    void cancelReservation_withValidRequest_shouldReturnOk() throws Exception {
        CancelReservationRequest request = new CancelReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + UUID.randomUUID() + "/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void cancelReservation_whenReservationNotFound_shouldReturnNotFound() throws Exception {
        CancelReservationRequest request = new CancelReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + RESERVATION_NOT_FOUND_ID + "/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void cancelReservation_whenAlreadyCancelled_shouldReturnBadRequest() throws Exception {
        CancelReservationRequest request = new CancelReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + RESERVATION_ALREADY_CANCELLED_ID + "/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cancelReservation_whenLastMinuteCancellation_shouldReturnBadRequest() throws Exception {
        CancelReservationRequest request = new CancelReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + RESERVATION_LAST_MINUTE_CANCELLATION_ID + "/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cancelReservation_withInvalidConfirmationCode_shouldReturnBadRequest() throws Exception {
        CancelReservationRequest request = new CancelReservationRequest(INVALID_CONFIRMATION_CODE);

        mockMvc.perform(put("/api/reservations/" + UUID.randomUUID() + "/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void checkInReservation_withValidRequest_shouldReturnOk() throws Exception {
        CheckInReservationRequest request = new CheckInReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + UUID.randomUUID() + "/check-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void checkInReservation_whenReservationNotFound_shouldReturnNotFound() throws Exception {
        CheckInReservationRequest request = new CheckInReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + RESERVATION_NOT_FOUND_ID + "/check-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void checkInReservation_withInvalidConfirmationCode_shouldReturnBadRequest() throws Exception {
        CheckInReservationRequest request = new CheckInReservationRequest(INVALID_CONFIRMATION_CODE);

        mockMvc.perform(put("/api/reservations/" + UUID.randomUUID() + "/check-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void checkInReservation_whenAlreadyCheckedIn_shouldReturnBadRequest() throws Exception {
        CheckInReservationRequest request = new CheckInReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + RESERVATION_ALREADY_CHECKED_IN_ID + "/check-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void checkInReservation_whenCheckInDateDoesNotMatch_shouldReturnBadRequest() throws Exception {
        CheckInReservationRequest request = new CheckInReservationRequest("123456789");

        mockMvc.perform(put("/api/reservations/" + RESERVATION_CHECK_IN_DATE_DOES_NOT_MATCH_ID + "/check-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void checkOutReservation_withValidReservation_shouldReturnOk() throws Exception {
        mockMvc.perform(put("/api/reservations/" + UUID.randomUUID() + "/check-out"))
                .andExpect(status().isOk());
    }

    @Test
    void checkOutReservation_whenReservationNotFound_shouldReturnNotFound() throws Exception {
        mockMvc.perform(put("/api/reservations/" + RESERVATION_NOT_FOUND_ID + "/check-out"))
                .andExpect(status().isNotFound());
    }

    @Test
    void checkOutReservation_whenAlreadyCheckedOut_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(put("/api/reservations/" + RESERVATION_ALREADY_CHECKED_OUT_ID + "/check-out"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findReservationAvailabilities_withValidRequest_shouldReturnAvailabilityList() throws Exception {
        FindReservationAvailabilitiesForSuitableRoomTypesRequest request = new FindReservationAvailabilitiesForSuitableRoomTypesRequest(
                2,
                0,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2)
        );

        mockMvc.perform(get("/api/reservations/availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomTypeInfoReadModel.roomTypeTitle").value("testRoomTypeTitle"))
                .andExpect(jsonPath("$[0].roomTypeInfoReadModel.roomTypeDescription").value("testRoomTypeDescription"))
                .andExpect(jsonPath("$[0].roomTypeInfoReadModel.roomTypePriceForNight").value(1))
                .andExpect(jsonPath("$[0].roomTypeInfoReadModel.roomTypeAdultCapacity").value(2))
                .andExpect(jsonPath("$[0].roomTypeInfoReadModel.roomTypeChildCapacity").value(1))
                .andExpect(jsonPath("$[0].remainingRooms").value(5))
                .andExpect(jsonPath("$[0].totalPriceForStayDate").value(10));
    }

    @Test
    void findReservationAvailabilities_withInvalidStayDate_shouldReturnBadRequest() throws Exception {
        FindReservationAvailabilitiesForSuitableRoomTypesRequest request = new FindReservationAvailabilitiesForSuitableRoomTypesRequest(
                2,
                0,
                INVALID_STAY_DATE_LOCAL_DATE,
                LocalDate.now().plusDays(2)
        );

        mockMvc.perform(get("/api/reservations/availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findReservationAvailabilities_withInvalidRequest_shouldReturnBadRequest() throws Exception {
        FindReservationAvailabilitiesForSuitableRoomTypesRequest request = new FindReservationAvailabilitiesForSuitableRoomTypesRequest(
                2,
                0,
                null,
                null
        );

        mockMvc.perform(get("/api/reservations/availability")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void findReservationsForUser_withValidRequest_shouldReturnReservationList() throws Exception {
        mockMvc.perform(get("/api/reservations/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].userId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].roomId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].reservationCode").value("11111111"))
                .andExpect(jsonPath("$[0].adultGuestCount").value(2))
                .andExpect(jsonPath("$[0].childGuestCount").value(1))
                .andExpect(jsonPath("$[0].status").value("CONFIRMED"))
                .andExpect(jsonPath("$[0].checkInDate").value("2030-01-01"))
                .andExpect(jsonPath("$[0].checkOutDate").value("2030-01-01"))
                .andExpect(jsonPath("$[0].customerFullName").value("John Doe"))
                .andExpect(jsonPath("$[0].customerPhoneNumber").value("0123456789"))
                .andExpect(jsonPath("$[0].customerEmailAddress").value("johndoe@gmail.com"));
    }

    @Test
    void findReservationsForCustomer_withValidRequest_shouldReturnReservationList() throws Exception {
        FindReservationsOfCustomerRequest request = new FindReservationsOfCustomerRequest(
            "John Doe",
            "0123456789"
        );

        mockMvc.perform(get("/api/reservations/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].userId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].roomId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].reservationCode").value("11111111"))
                .andExpect(jsonPath("$[0].adultGuestCount").value(2))
                .andExpect(jsonPath("$[0].childGuestCount").value(1))
                .andExpect(jsonPath("$[0].status").value("CONFIRMED"))
                .andExpect(jsonPath("$[0].checkInDate").value("2030-01-01"))
                .andExpect(jsonPath("$[0].checkOutDate").value("2030-01-01"))
                .andExpect(jsonPath("$[0].customerFullName").value("John Doe"))
                .andExpect(jsonPath("$[0].customerPhoneNumber").value("0123456789"))
                .andExpect(jsonPath("$[0].customerEmailAddress").value("johndoe@gmail.com"));
    }

    @Test
    void findByReservationCode_withValidRequest_shouldReturnReservationList() throws Exception {
        mockMvc.perform(get("/api/reservations/12345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("userId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("roomId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("reservationCode").value("11111111"))
                .andExpect(jsonPath("adultGuestCount").value(2))
                .andExpect(jsonPath("childGuestCount").value(1))
                .andExpect(jsonPath("status").value("CONFIRMED"))
                .andExpect(jsonPath("checkInDate").value("2030-01-01"))
                .andExpect(jsonPath("checkOutDate").value("2030-01-01"))
                .andExpect(jsonPath("customerFullName").value("John Doe"))
                .andExpect(jsonPath("customerPhoneNumber").value("0123456789"))
                .andExpect(jsonPath("customerEmailAddress").value("johndoe@gmail.com"));
    }

    @Test
    void findByReservationCode_whenReservationDoesNotExist_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/reservations/" + RESERVATION_NOT_FOUND_RESERVATION_CODE))
                .andExpect(status().isNotFound());
    }
}