package com.mstftrgt.hotelreservationsystem.persistence;

import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomJpaRepository;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomDataAdapter implements RoomRepository {

    private final ApplicationEventPublisher publisher;
    private final RoomJpaRepository roomJpaRepository;

    @Override
    public void save(Room room) {
        roomJpaRepository.save(RoomEntity.from(room));

        room.getDomainEvents().forEach(publisher::publishEvent);
        room.clearEvents();
    }

    @Override
    public void remove(Room room) {
        roomJpaRepository.deleteById(room.getId());

        room.getDomainEvents().forEach(publisher::publishEvent);
        room.clearEvents();
    }

    @Override
    public Optional<Room> findById(UUID roomId) {
        return roomJpaRepository.findById(roomId)
                .map(RoomEntity::toModel);
    }

    @Override
    public List<Room> getAllRoomsOfRoomType(UUID roomTypeId) {
        return roomJpaRepository.findAllByRoomTypeId(roomTypeId)
                .stream()
                .map(RoomEntity::toModel)
                .toList();
    }

}
