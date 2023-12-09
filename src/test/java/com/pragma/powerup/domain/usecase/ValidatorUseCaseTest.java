package com.pragma.powerup.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorUseCaseTest {

    private ValidatorUseCase validatorUseCase;
    @BeforeEach
    void setUp() {
        validatorUseCase = new ValidatorUseCase();
    }

    @Test
    void rolesValidatorAdminCreatesAdmin() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_administrador",
                        "administrador"
                );
        assertTrue(result);
    }

    @Test
    void rolesValidatorAdminCreatesOwner() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_administrador",
                        "propietario"
                );
        assertTrue(result);
    }

    @Test
    void rolesValidatorOwnerCreatesEmployee() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_propietario",
                        "empleado"
                );
        assertTrue(result);
    }

    @Test
    void rolesValidatorAdminCreatesEmployee() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_administrador",
                        "empleado"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorAdminCreatesClient() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_administrador",
                        "cliente"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorOwnerCreatesClient() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_propietario",
                        "cliente"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorClientCreatesAdmin() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_cliente",
                        "administrador"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorClientCreatesOwner() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_cliente",
                        "propietario"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorClientCreatesEmployee() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_cliente",
                        "empleado"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorClientCreatesClient() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "ROLE_cliente",
                        "cliente"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorEmptyCreatesEmployee() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "",
                        "empleado"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorEmptyCreatesAdmin() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "",
                        "administrador"
                );
        assertFalse(result);
    }

    @Test
    void rolesValidatorEmptyCreatesEmpty() {
        boolean result =
                validatorUseCase.rolesValidator(
                        "",
                        ""
                );
        assertFalse(result);
    }

    @Test
    void ageValidatorGreaterThan18() {
        boolean result = validatorUseCase.ageValidator(LocalDate.of(2002,11,29));
        assertTrue(result);
    }

    @Test
    void ageValidatorUnder18() {
        boolean result = validatorUseCase.ageValidator(LocalDate.of(2013,11,29));
        assertFalse(result);
    }

    @Test
    void ageValidator18Recently() {
        boolean result = validatorUseCase.ageValidator(LocalDate.of(2005,12,8));
        assertTrue(result);
    }

    @Test
    void ageValidatorCloseTo18() {
        boolean result = validatorUseCase.ageValidator(LocalDate.of(2005,12,10));
        assertFalse(result);
    }
}