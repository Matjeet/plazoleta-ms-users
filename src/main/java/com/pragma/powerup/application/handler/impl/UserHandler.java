package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IRoleDtoMapper;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IRoleDtoMapper roleDtoMapper;
    @Override
    public void saveUser(UserRequestDTO userRequestDto) {
        Role role = roleServicePort.saveRol(userRequestMapper.toRole(userRequestDto));
        User user = userRequestMapper.toUser(userRequestDto);
        user.setRoleId(role.getId());
        userServicePort.saveUser(user);
    }
}
