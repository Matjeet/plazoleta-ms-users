package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerRestController {

    private final IUserHandler userHandler;

    public ResponseEntity<Boolean> validateOwnerRole(@PathVariable int id){
        boolean ownerRole = userHandler.validateOwnerRole(id);
        if(ownerRole){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(true);
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(false);
    }
}
