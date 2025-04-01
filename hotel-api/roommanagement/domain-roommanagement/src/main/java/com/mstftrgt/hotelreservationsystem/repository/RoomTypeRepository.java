package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.domain.Repository;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.dto.RoomTypeCreate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomTypeRepository extends Repository {

    Optional<RoomType> findById(UUID roomTypeId);

    RoomType findByRoomId(UUID roomId);

    void save(RoomType roomType);

    List<RoomType> findAll();
}
