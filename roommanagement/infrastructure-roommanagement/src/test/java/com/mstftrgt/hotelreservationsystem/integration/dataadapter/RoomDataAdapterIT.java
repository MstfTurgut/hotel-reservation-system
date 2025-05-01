package com.mstftrgt.hotelreservationsystem.integration.dataadapter;


import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.persistence.RoomDataAdapter;
import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("dataAdapterIntegrationTest")
public class RoomDataAdapterIT {

    @MockitoBean
    private ApplicationEventPublisher publisher;

    private RoomDataAdapter roomDataAdapter;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @BeforeEach
    void setUp() {
        this.roomDataAdapter = new RoomDataAdapter(publisher, roomJpaRepository);
    }

    @Test
    void save_shouldPersistRoom() {
        Room room = Room.builder()
                .id(UUID.randomUUID())
                .roomTypeId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .roomNumber("1")
                .build();

        roomDataAdapter.save(room);

        Optional<RoomEntity> result = roomJpaRepository.findById(room.getId());

        assertTrue(result.isPresent());
    }

    @Test
    void remove_shouldRemoveRoom() {
        Optional<Room> optionalRoom = roomJpaRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111")).map(RoomEntity::toModel);
        assertTrue(optionalRoom.isPresent(), "Room should be present before deletion");

        roomDataAdapter.remove(optionalRoom.get());

        Optional<RoomEntity> result = roomJpaRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_shouldRetrieveRoom() {
        Optional<Room> optionalRoom = roomDataAdapter.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        assertTrue(optionalRoom.isPresent());

        Room room = optionalRoom.get();
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), room.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), room.getRoomTypeId());
        assertEquals("101", room.getRoomNumber());
    }

    @Test
    void getAllRoomsOfRoomType_shouldRetrieveAllRoomsOfRoomType() {
        List<Room> roomList = roomDataAdapter.getAllRoomsOfRoomType(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        assertEquals(1, roomList.size());
        Room room = roomList.get(0);
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), room.getId());
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), room.getRoomTypeId());
        assertEquals("101", room.getRoomNumber());
    }
}
