package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);

    boolean validateOwnerRole(int id);

    boolean validateRestaurantEmployee(int idEmployee, int idRestaurant);

    boolean validateClientRole(int idClient);

    User getUser(int idUser);
}
