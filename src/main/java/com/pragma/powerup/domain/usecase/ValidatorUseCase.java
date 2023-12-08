package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IValidatorServicePort;

import java.time.LocalDate;
import java.time.Period;

public class ValidatorUseCase implements IValidatorServicePort {

    @Override
    public boolean rolesValidator(String tokenRole, String registerRole) {
        return tokenRole.equals("ROLE_adminsitrador") && registerRole.equals("administrador") ||
                tokenRole.equals("ROLE_administrador") && registerRole.equals("propietario") ||
                tokenRole.equals("ROLE_propietario") && registerRole.equals("empleado");
    }

    @Override
    public boolean ageValidator(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }
}
