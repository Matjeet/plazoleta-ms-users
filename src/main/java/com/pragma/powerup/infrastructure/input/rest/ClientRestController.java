package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.response.UserInfoResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/v1")
@RequiredArgsConstructor
public class ClientRestController {

    private final IUserHandler userHandler;

    @PreAuthorize("hasRole('ROLE_empleado')")
    @GetMapping("/info/{idClient}")
    public ResponseEntity<UserInfoResponseDto> getClient(@PathVariable int idClient){
        UserInfoResponseDto result = userHandler.getClient(idClient);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
