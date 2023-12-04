package com.pragma.powerup.domain.api;

import java.time.LocalDate;

public interface IValidatorServicePort {

    boolean rolesValidator(String tokenRole, String registerRole);

    boolean ageValidator(LocalDate birthDate);
}
