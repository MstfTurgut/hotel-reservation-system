package com.mstftrgt.hotelreservationsystem.persistence;

import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomJpaRepository;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomDataAdapter implements RoomRepository {

    private final RoomJpaRepository roomJpaRepository;

    @Override
    public Optional<Room> findById(UUID roomId) {
        return roomJpaRepository.findById(roomId)
                .map(RoomEntity::toModel);
    }

    @Override
    public void save(Room room) {
        roomJpaRepository.save(RoomEntity.from(room));
    }

    @Override
    public List<Room> getAllRoomsOfRoomType(UUID roomTypeId) {
        return roomJpaRepository.findAllByRoomTypeId(roomTypeId)
                .stream()
                .map(RoomEntity::toModel)
                .toList();
    }

    @Override
    public void remove(Room room) {
        roomJpaRepository.delete(RoomEntity.from(room));
    }

    @Override
    public void removeAllByRoomTypeId(UUID roomTypeId) {
        List<RoomEntity> rooms = roomJpaRepository.findAllByRoomTypeId(roomTypeId);
        roomJpaRepository.deleteAll(rooms);
    }

}
