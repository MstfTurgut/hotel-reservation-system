package com.mstftrgt.hotelreservationsystem.integration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBus;
import com.mstftrgt.hotelreservationsystem.generic.application.CommandBusImpl;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBus;
import com.mstftrgt.hotelreservationsystem.generic.application.QueryBusImpl;
import com.mstftrgt.hotelreservationsystem.presentation.dto.AddNewRoomTypeRequest;
import com.mstftrgt.hotelreservationsystem.presentation.dto.ModifyRoomTypeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static com.mstftrgt.hotelreservationsystem.integration.controller.FakeExceptionFields.ROOM_TYPE_NOT_FOUND_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("controllerIntegrationTest")
@Import({CommandBusImpl.class, QueryBusImpl.class})
public class RoomTypeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private QueryBus queryBus;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addNewRoomType_withValidRequest_shouldReturnCreated() throws Exception {
        AddNewRoomTypeRequest request = new AddNewRoomTypeRequest(
                "testTitle",
                "testDescription",
                BigDecimal.TEN,
                2,
                1
        );

        mockMvc.perform(post("/api/room-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void modifyRoomType_withValidRequest_shouldReturnOk() throws Exception {
        ModifyRoomTypeRequest request = new ModifyRoomTypeRequest(
                "testTitle",
                "testDescription",
                BigDecimal.TEN,
                2,
                1
        );

        mockMvc.perform(put("/api/room-types/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void modifyRoomType_whenRoomTypeNotFound_shouldReturnNotFound() throws Exception {
        ModifyRoomTypeRequest request = new ModifyRoomTypeRequest(
                "testTitle",
                "testDescription",
                BigDecimal.TEN,
                2,
                1
        );

        mockMvc.perform(put("/api/room-types/" + ROOM_TYPE_NOT_FOUND_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void removeRoomType_shouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/room-types/" + UUID.randomUUID()))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeRoomType_whenRoomTypeNotFound_shouldReturnNotFound() throws Exception {
        mockMvc.perform(delete("/api/room-types/" + ROOM_TYPE_NOT_FOUND_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllRoomTypes_shouldReturnAllRoomTypes() throws Exception {
        mockMvc.perform(get("/api/room-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].title").value("testTitle"))
                .andExpect(jsonPath("$[0].description").value("testDescription"))
                .andExpect(jsonPath("$[0].priceForNight").value(10))
                .andExpect(jsonPath("$[0].adultCapacity").value(2))
                .andExpect(jsonPath("$[0].childCapacity").value(1));
    }

    @Test
    void getAllRoomsOfRoomTypes_shouldReturnAllRoomsOfRoomType() throws Exception {
        UUID roomTypeId = UUID.fromString("11111111-1111-1111-1111-111111111111");

        mockMvc.perform(get("/api/room-types/" + roomTypeId + "/rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].roomTypeId").value("11111111-1111-1111-1111-111111111111"))
                .andExpect(jsonPath("$[0].roomNumber").value("101"));
    }

}
