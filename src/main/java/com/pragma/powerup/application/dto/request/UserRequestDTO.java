package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class UserRequestDTO {
    private String id;
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Role role;
}
