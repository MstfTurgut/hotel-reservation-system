package com.mstftrgt.hotelreservationsystem.generic.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}