package com.mstftrgt.hotelreservationsystem.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBusImpl;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBusImpl;
import com.mstftrgt.hotelreservationsystem.presentation.dto.AddNewRoomRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.ROOM_NOT_FOUND_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("controllerIntegrationTest")
@Import({CommandBusImpl.class, QueryBusImpl.class})
public class RoomControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private QueryBus queryBus;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addNewRoom_withValidRequest_shouldReturnCreated() throws Exception {
        AddNewRoomRequest request = new AddNewRoomRequest(
                "101",
                UUID.randomUUID()
        );

        mockMvc.perform(post("/api/rooms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void removeRoom_withValidRequest_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/rooms/" + UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeRoom_whenRoomNotFound_shouldReturnNotFound() throws Exception {
        mockMvc.perform(delete("/api/rooms/" + ROOM_NOT_FOUND_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void findRoomById_shouldReturnRoom() throws Exception {
        mockMvc.perform(get("/api/rooms/" + UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("roomTypeId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("roomNumber").value("101"));
    }

    @Test
    void findRoomById_whenRoomNotFound_shouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/api/rooms/" + ROOM_NOT_FOUND_ID))
                .andExpect(status().isNotFound());
    }
}
