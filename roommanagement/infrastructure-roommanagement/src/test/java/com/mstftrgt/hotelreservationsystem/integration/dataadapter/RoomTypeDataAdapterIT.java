package com.mstftrgt.hotelreservationsystem.integration.dataadapter;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.persistence.RoomTypeDataAdapter;
import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomTypeEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomJpaRepository;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomTypeJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Sql(scripts = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:clear.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ActiveProfiles("dataAdapterIntegrationTest")
public class RoomTypeDataAdapterIT {

    @MockitoBean
    private ApplicationEventPublisher publisher;

    private RoomTypeDataAdapter roomTypeDataAdapter;

    @Autowired
    private RoomJpaRepository roomJpaRepository;

    @Autowired
    private RoomTypeJpaRepository roomTypeJpaRepository;

    @BeforeEach
    void setUp() {
        this.roomTypeDataAdapter = new RoomTypeDataAdapter(publisher, roomJpaRepository, roomTypeJpaRepository);
    }


    @Test
    void save_shouldPersistRoomType() {
        RoomType roomType = RoomType.builder()
                .id(UUID.randomUUID())
                .title("testTitle")
                .description("testDescription")
                .priceForNight(BigDecimal.TEN)
                .numberOfRooms(1)
                .adultCapacity(2)
                .childCapacity(1)
                .build();

        roomTypeDataAdapter.save(roomType);

        Optional<RoomTypeEntity> result = roomTypeJpaRepository.findById(roomType.getId());

        assertTrue(result.isPresent());
    }

    @Test
    void remove_shouldRemoveRoomType() {
        Optional<RoomType> optionalRoomType = roomTypeJpaRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111")).map(RoomTypeEntity::toModel);
        assertTrue(optionalRoomType.isPresent(), "Room type should be present before deletion");

        roomTypeDataAdapter.remove(optionalRoomType.get());

        Optional<RoomTypeEntity> result = roomTypeJpaRepository.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));

        assertTrue(result.isEmpty());
    }

    @Test
    void findById_shouldRetrieveRoomType() {
        Optional<RoomType> optionalRoomType = roomTypeDataAdapter.findById(UUID.fromString("11111111-1111-1111-1111-111111111111"));
        assertTrue(optionalRoomType.isPresent());

        RoomType roomType = optionalRoomType.get();
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), roomType.getId());
        assertEquals("testTitle", roomType.getTitle());
        assertEquals("testDescription", roomType.getDescription());
        assertEquals(0, BigDecimal.TEN.compareTo(roomType.getPriceForNight()));
        assertEquals(1, roomType.getNumberOfRooms());
        assertEquals(2, roomType.getAdultCapacity());
        assertEquals(1, roomType.getChildCapacity());
    }

    @Test
    void findAll_shouldRetrieveAllRoomTypes() {
        List<RoomType> roomTypeList = roomTypeDataAdapter.findAll();

        assertEquals(1, roomTypeList.size());
        RoomType roomType = roomTypeList.get(0);
        assertEquals(UUID.fromString("11111111-1111-1111-1111-111111111111"), roomType.getId());
        assertEquals("testTitle", roomType.getTitle());
        assertEquals("testDescription", roomType.getDescription());
        assertEquals(0, BigDecimal.TEN.compareTo(roomType.getPriceForNight()));
        assertEquals(1, roomType.getNumberOfRooms());
        assertEquals(2, roomType.getAdultCapacity());
        assertEquals(1, roomType.getChildCapacity());
    }

}
