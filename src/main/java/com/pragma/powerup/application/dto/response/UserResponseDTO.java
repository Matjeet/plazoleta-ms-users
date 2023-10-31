package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private String password;
    private int rolId;
}
