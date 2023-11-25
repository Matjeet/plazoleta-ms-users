package com.pragma.powerup.application.handler;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;

public interface ITokenHandler {

    String createToken(User user, Role role);
}
