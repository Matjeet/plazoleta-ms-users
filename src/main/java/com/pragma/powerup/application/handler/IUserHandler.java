package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.response.AuthResponseDto;
import com.pragma.powerup.application.dto.response.UserInfoResponseDto;

public interface IUserHandler {

    AuthResponseDto saveUser(RegisterRequestDto registerRequestDto);

    AuthResponseDto login(LoginRequestDto loginRequestDto);

    boolean validateOwnerRole(int id);

    boolean validateRestaurantEmployee(int idEmployee, int idRestaurant);

    UserInfoResponseDto getClient(int idClient);
}
