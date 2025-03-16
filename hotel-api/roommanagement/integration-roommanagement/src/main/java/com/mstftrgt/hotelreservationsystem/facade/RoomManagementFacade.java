package com.mstftrgt.hotelreservationsystem.facade;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomManagementFacade {


    RoomTypeContract findRoomTypeByRoomId(UUID roomId);

    List<UUID> findAllRoomIdsForRoomType(UUID roomTypeId);

    List<RoomTypeContract> findAllSuitableRoomTypes(int adultGuestCount, int childGuestCount);
}
