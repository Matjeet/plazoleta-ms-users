package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.User;

public interface IUserServicePort {

    void saveUser(User user);

    boolean validateOwnerRole(int id);
}
