package com.pragma.powerup.infrastructure.input.rest;

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
@RequestMapping("/employee/v1")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final IUserHandler userHandler;

    
    @PreAuthorize("hasRole('ROLE_empleado')")
    @GetMapping("/validate-restaurant/{idEmployee}/{idRestaurant}")
    public ResponseEntity<Boolean> validateRestaurantEmployee(
            @PathVariable int idEmployee,
            @PathVariable int idRestaurant
    ){
     return ResponseEntity
             .status(HttpStatus.OK)
             .body(userHandler.validateRestaurantEmployee(idEmployee, idRestaurant));
    }
}
