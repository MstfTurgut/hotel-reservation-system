package com.mstftrgt.hotelreservationsystem.repository;

import com.mstftrgt.hotelreservationsystem.domain.Repository;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.dto.RoomTypeCreate;

import java.util.List;
import java.util.Optional;

public interface RoomTypeRepository extends Repository {

    Optional<RoomType> findById(long roomTypeId);

    Optional<RoomType> findByRoomId(long roomId);

    void save(RoomTypeCreate roomTypeCreate);

    void update(RoomType roomType);

    List<RoomType> findAll();
}
