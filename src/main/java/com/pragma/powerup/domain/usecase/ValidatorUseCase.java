package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IValidatorServicePort;

import java.time.LocalDate;

public class ValidatorUseCase implements IValidatorServicePort {
   
    @Override
    public boolean rolesValidator(String tokenRole, String registerRole) {
        return false;
    }

    @Override
    public boolean ageValidator(LocalDate birthDate) {
        return false;
    }
}
