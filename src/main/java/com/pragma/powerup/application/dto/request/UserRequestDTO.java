package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRequestDTO {
    private String id;
    private String name;
    private String lastName;
    private String documentId;
    private String phoneNumber;
    private Date birthDate;
    private String email;
    private String password;
    private Role role;
}
