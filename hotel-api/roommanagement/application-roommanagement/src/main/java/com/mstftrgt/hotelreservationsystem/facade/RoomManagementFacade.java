package com.mstftrgt.hotelreservationsystem.facade;


import java.util.List;
import java.util.Optional;

public interface RoomManagementFacade {

    Optional<RoomTypeContract> findRoomTypeById(long roomTypeId);

    Optional<RoomTypeContract> findRoomTypeByRoomId(long roomId);

    List<Long> findAllRoomIdsForRoomType(long roomTypeId);

    List<RoomTypeContract> findAllSuitableRoomTypes(int adultGuestCount, int childGuestCount);
}
