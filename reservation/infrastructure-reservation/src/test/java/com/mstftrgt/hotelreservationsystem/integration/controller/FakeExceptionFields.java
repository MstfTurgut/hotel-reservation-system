package com.mstftrgt.hotelreservationsystem.integration.controller;

import java.time.LocalDate;
import java.util.UUID;

public class FakeExceptionFields {

    public final static UUID RESERVATION_NOT_AVAILABLE_ROOM_TYPE_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    public final static UUID RESERVATION_NOT_FOUND_ID = UUID.fromString("00000000-0000-0000-0000-000000000002");

    public final static UUID RESERVATION_ALREADY_CANCELLED_ID = UUID.fromString("00000000-0000-0000-0000-000000000003");

    public final static UUID RESERVATION_LAST_MINUTE_CANCELLATION_ID = UUID.fromString("00000000-0000-0000-0000-000000000004");

    public final static String INVALID_CONFIRMATION_CODE = "XXXXXXXX";

    public final static UUID RESERVATION_ALREADY_CHECKED_IN_ID = UUID.fromString("00000000-0000-0000-0000-000000000005");

    public final static UUID RESERVATION_CHECK_IN_DATE_DOES_NOT_MATCH_ID = UUID.fromString("00000000-0000-0000-0000-000000000006");

    public final static UUID RESERVATION_ALREADY_CHECKED_OUT_ID = UUID.fromString("00000000-0000-0000-0000-000000000007");

    public static final LocalDate INVALID_STAY_DATE_LOCAL_DATE = LocalDate.of(2000, 1, 1);

    public final static String RESERVATION_NOT_FOUND_RESERVATION_CODE = "99999999";

}
