package com.mstftrgt.hotelreservationsystem.facade;

import com.mstftrgt.hotelreservationsystem.exception.RoomNotFoundException;
import com.mstftrgt.hotelreservationsystem.model.Room;
import com.mstftrgt.hotelreservationsystem.model.RoomType;
import com.mstftrgt.hotelreservationsystem.repository.RoomRepository;
import com.mstftrgt.hotelreservationsystem.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RoomManagementFacadeImpl implements RoomManagementFacade {

    private final RoomTypeRepository roomTypeRepository;
    private final RoomRepository roomRepository;


    @Override
    public RoomTypeContract findRoomTypeByRoomId(UUID roomId) {
        return buildRoomTypeContract(roomTypeRepository.findByRoomId(roomId));
    }

    @Override
    public List<UUID> findAllRoomIdsForRoomType(UUID roomTypeId) {
        return roomRepository.getAllRoomsOfRoomType(roomTypeId)
                .stream()
                .map(Room::getId)
                .toList();
    }

    @Override
    public List<RoomTypeContract> findAllSuitableRoomTypes(int adultGuestCount, int childGuestCount) {
        List<RoomType> allRoomTypes = roomTypeRepository.findAll();

        return allRoomTypes
                .stream()
                .filter(roomType -> roomType.canFit(adultGuestCount, childGuestCount))
                .map(this::buildRoomTypeContract)
                .toList();
    }

    private RoomTypeContract buildRoomTypeContract(RoomType roomType) {
        return RoomTypeContract.builder()
                .id(roomType.getId())
                .title(roomType.getTitle())
                .description(roomType.getDescription())
                .priceForNight(roomType.getPriceForNight())
                .numberOfRooms(roomType.getNumberOfRooms())
                .adultCapacity(roomType.getAdultCapacity())
                .childCapacity(roomType.getChildCapacity())
                .build();
    }

}
