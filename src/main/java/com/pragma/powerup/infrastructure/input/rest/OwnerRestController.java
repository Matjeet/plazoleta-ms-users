package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Validate whether or no a user is an owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "It's an owner", content = @Content),
            @ApiResponse(responseCode = "401", description = "It's not an owner", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have permissions", content = @Content)
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_administrador')")
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
