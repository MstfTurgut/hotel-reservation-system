package com.mstftrgt.hotelreservationsystem.query.user.find;

import com.mstftrgt.hotelreservationsystem.Query;
import java.util.UUID;

public record FindUserQuery(UUID userId) implements Query {}
