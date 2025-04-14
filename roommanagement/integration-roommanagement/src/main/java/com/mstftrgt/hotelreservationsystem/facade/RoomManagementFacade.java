package com.mstftrgt.hotelreservationsystem.facade;


import com.mstftrgt.hotelreservationsystem.contract.RoomTypeContract;

import java.util.List;
import java.util.UUID;

public interface RoomManagementFacade {


    RoomTypeContract findRoomTypeByRoomId(UUID roomId);

    List<UUID> findAllRoomIdsForRoomType(UUID roomTypeId);

    List<RoomTypeContract> findAllSuitableRoomTypes(int adultGuestCount, int childGuestCount);
}
