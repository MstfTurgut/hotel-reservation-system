package com.mstftrgt.hotelreservationsystem.authentication.model;

import io.craftgate.modulith.messaging.api.model.AggregateRoot;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@ToString
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class User extends AggregateRoot {

    private Long id;
    private String email;
    private String password;
    private String role;
}
