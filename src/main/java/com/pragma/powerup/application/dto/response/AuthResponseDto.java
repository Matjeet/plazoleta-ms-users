package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.dto.RoleDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthResponseDto {
    private String token;
}
