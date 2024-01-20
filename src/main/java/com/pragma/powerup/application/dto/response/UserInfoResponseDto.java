package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfoResponseDto {

    private int id;
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private int roleId;
    private Integer restaurantId;
}
