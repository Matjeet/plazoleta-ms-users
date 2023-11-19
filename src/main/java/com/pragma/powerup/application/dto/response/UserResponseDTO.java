package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.dto.RoleDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private RoleDTO role;
}
