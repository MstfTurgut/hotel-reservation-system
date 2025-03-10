# Hotel Reservation System

A software system for an imaginary hotel. Supports in-hotel, call and online reservation.

<br>
<br>

![EventStorming](assets/es.png)

![Model](assets/model.png)

<br>
<br>

You can access to event storming in <a href="https://miro.com/app/board/uXjVLFt3NmM=/?share_link_id=196794179204">here.</a>

<br>

# Scenarios

## Reservation with Call

1. Customer calls the hotel.
2. Receptionist asks the customer, "How can I help you, sir?"
3. Customer says, "I want to reserve a room."
4. Receptionist asks, "Can you specify the stay date and the number of guests, sir?"
5. Customer specifies the stay date and the number of guests (number of adults and children separately).
6. Receptionist retrieves all available room types with:
   - Number of remaining rooms.
   - Potential total price of each room type.
7. Receptionist explains to the customer all the available room types.
8. Customer chooses a room type.
9. Receptionist asks for the customer's:
   - Name, surname.
   - Phone number, email address.
   - Card information for payment.
10. Customer provides the requested information.
11. Receptionist makes the reservation in the system and says:
    - "Your reservation has been completed successfully, sir. The confirmation code and all other information will be sent to your phone number and email."
12. Call ends.

### Use Cases Involved
- `RetrieveAvailableRoomTypes`
- `CreateReservation`
- `ProcessPayment`
- `NotifyCustomer`

<br>

## Online Reservation

1. Customer logs in to the system.
2. Customer searches for available rooms by specifying:
   - Stay date.
   - Number of guests (number of adults and children separately).
3. System retrieves all available room types with:
   - Number of remaining rooms.
   - Potential total price of each room type.
4. Customer selects a room type.
5. Customer enters:
   - Personal information.
   - Payment information.
6. Customer completes the reservation.

### Use Cases Involved
- `Login`
- `RetrieveAvailableRoomTypes`
- `CreateReservation`
- `ProcessPayment`
- `NotifyCustomer`

<br>

## Face-to-Face Reservation (Typically Same-Day Check-In)

1. Customer enters the hotel for a face-to-face reservation.
2. Receptionist asks the customer, "How can I help you, sir?"
3. Customer says, "I want to reserve a room."
4. Receptionist asks, "Can you specify the stay date and the number of guests, sir?"
5. Customer specifies the stay date and the number of guests (number of adults and children separately).
6. Receptionist retrieves all available room types with:
   - Number of remaining rooms.
   - Potential total price of each room type.
7. Receptionist explains to the customer all the available room types.
8. Customer chooses a room type.
9. Receptionist asks for the customer's:
   - Name, surname.
   - Phone number, email address.
10. Customer provides the requested information.
11. Receptionist makes the reservation in the system and says:
    - "Your reservation has been completed successfully, sir. The confirmation code and all other information will be sent to your phone number and email."
12. Receptionist collects payment (either via cash or card).
13. Customer leaves.

### Use Cases Involved
- `RetrieveAvailableRoomTypes`
- `CreateInHotelReservation`
- `NotifyCustomer`

<br>

## Cancellation by Call

1. Customer calls the hotel.
2. Customer provides their information and specifies the reservation to cancel.
3. Receptionist searches for reservations using the customer’s information and finds the reservation to cancel.
4. Receptionist asks for the confirmation code.
5. Customer provides the confirmation code.
6. Receptionist cancels the reservation.
7. Call ends.

### Use Cases Involved
- `RetrieveReservationsOfCustomer`
- `CancelReservation`
- `RefundPayment`

<br>

## Online Cancellation

1. Customer logs in to the system.
2. Customer selects "My Reservations."
3. Customer chooses the reservation to cancel.
4. System cancels the reservation (if at least 3 days before the stay) and triggers a payment refund.

### Use Cases Involved
- `Login`
- `RetrieveReservationsOfUser`
- `CancelReservation`
- `RefundPayment`

<br>

## Customer Checking In

1. Customer approaches the receptionist and says they want to check in.
2. Customer provides the confirmation number.
3. Receptionist marks the reservation as checked in.

### Use Cases Involved
- `RetrieveReservationsOfCustomer`
- `ReservationCheckIn`

<br>

## Customer Checking Out

1. Customer informs the receptionist they want to check out.
2. Receptionist marks the reservation as checked out, making the room available for future reservations.

### Use Cases Involved
- `RetrieveReservationsOfCustomer`
- `ReservationCheckOut`

<br>

## com.mstftrgt.hotelreservationsystem.model.Room Type Management

Manager can manage room types.

- Retrieve room types.
- Add a room type.
- Update a room type.
- Delete a room type.

### Use Cases Involved
- `RetrieveRoomTypes`
- `AddRoomType`
- `ModifyRoomType`
- `DeleteRoomType`

<br>

## com.mstftrgt.hotelreservationsystem.model.Room Management

Manager can manage rooms.

- Retrieve rooms.
- Add a room.
- Delete a room.

### Use Cases Involved
- `RetrieveRooms`
- `AddRoom`
- `DeleteRoom`

<br>
<br>

Mustafa Turgut
mstftrgt00@gmail.com
