package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.RegisterRequestDto;

public interface IUserHandler {

    void saveUser(RegisterRequestDto registerRequestDto);
}
