package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);

    boolean validateOwnerRole(int id);
}
