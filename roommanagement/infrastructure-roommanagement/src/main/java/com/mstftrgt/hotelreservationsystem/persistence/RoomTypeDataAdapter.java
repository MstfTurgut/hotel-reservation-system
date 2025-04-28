package com.mstftrgt.hotelreservationsystem.persistence;

import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.persistence.entity.RoomTypeEntity;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomJpaRepository;
import com.mstftrgt.hotelreservationsystem.persistence.repository.RoomTypeJpaRepository;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomTypeDataAdapter implements RoomTypeRepository {

    private final ApplicationEventPublisher publisher;
    private final RoomJpaRepository roomJpaRepository;
    private final RoomTypeJpaRepository roomTypeJpaRepository;

    @Override
    public void save(RoomType roomType) {
        roomTypeJpaRepository.save(RoomTypeEntity.from(roomType));

        roomType.getDomainEvents().forEach(publisher::publishEvent);
        roomType.clearEvents();
    }

    @Override
    public void remove(RoomType roomType) {
        roomTypeJpaRepository.deleteById(roomType.getId());

        roomType.getDomainEvents().forEach(publisher::publishEvent);
        roomType.clearEvents();
    }

    @Override
    public Optional<RoomType> findById(UUID roomTypeId) {
        return roomTypeJpaRepository.findById(roomTypeId)
                .map(RoomTypeEntity::toModel);
    }

    @Override
    public RoomType findByRoomId(UUID roomId) {
        UUID roomTypeId = roomJpaRepository.findRoomTypeIdByRoomId(roomId);

        return roomTypeJpaRepository.findById(roomTypeId)
                .map(RoomTypeEntity::toModel)
                .orElseThrow(() -> new IllegalArgumentException("RoomType not found"));
    }

    @Override
    public List<RoomType> findAll() {
        return roomTypeJpaRepository.findAll()
                .stream()
                .map(RoomTypeEntity::toModel)
                .toList();
    }
}
