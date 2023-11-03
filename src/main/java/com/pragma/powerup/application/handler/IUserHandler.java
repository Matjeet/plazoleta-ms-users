package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDTO;

public interface IUserHandler {

    void saveUser(UserRequestDTO userRequestDto);
}
