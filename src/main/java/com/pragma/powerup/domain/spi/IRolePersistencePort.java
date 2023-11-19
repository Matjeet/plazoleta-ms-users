package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Role;

public interface IRolePersistencePort {
    Role saveRol(Role role);

    int getRoleId(String name);
}
