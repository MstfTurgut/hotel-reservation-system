package com.mstftrgt.hotelreservationsystem.persistence;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomTypeEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomTypeJpaRepository;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomTypeDataAdapter implements RoomTypeRepository {

    private final RoomTypeJpaRepository roomTypeJpaRepository;

    @Override
    public Optional<RoomType> findById(UUID roomTypeId) {
        return roomTypeJpaRepository.findById(roomTypeId)
                .map(RoomTypeEntity::toModel);
    }

    @Override
    public RoomType findByRoomId(UUID roomId) {
        return roomTypeJpaRepository.findById(roomId)
                .map(RoomTypeEntity::toModel)
                .orElseThrow(() -> new IllegalArgumentException("RoomType not found"));
    }

    @Override
    public void save(RoomType roomType) {
        roomTypeJpaRepository.save(RoomTypeEntity.from(roomType));
    }

    @Override
    public List<RoomType> findAll() {
        return roomTypeJpaRepository.findAll()
                .stream()
                .map(RoomTypeEntity::toModel)
                .toList();
    }

    @Override
    public void remove(RoomType roomType) {
        roomTypeJpaRepository.delete(RoomTypeEntity.from(roomType));
    }
}
