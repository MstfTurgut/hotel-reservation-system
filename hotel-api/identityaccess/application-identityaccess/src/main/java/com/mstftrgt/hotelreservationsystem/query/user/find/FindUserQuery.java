package com.mstftrgt.hotelreservationsystem.query.user.find;

import com.mstftrgt.hotelreservationsystem.Query;
import lombok.Data;

public record FindUserQuery(long userId) implements Query {}
