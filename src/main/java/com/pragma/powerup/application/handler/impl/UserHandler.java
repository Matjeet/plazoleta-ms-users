package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.response.AuthResponseDto;
import com.pragma.powerup.application.handler.IPasswordHandler;
import com.pragma.powerup.application.handler.ITokenHandler;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IRoleDtoMapper;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IAuthResponseMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IPasswordHandler passwordHandler;
    private final IAuthResponseMapper authResponseMapper;
    private final ITokenHandler tokenHandler;
    private final UserDetailsService userDetailsService;
    private final IRoleDtoMapper roleDtoMapper;
    @Override
    public AuthResponseDto saveUser(RegisterRequestDto registerRequestDto) {
        Role role = roleServicePort.saveRol(userRequestMapper.toRole(registerRequestDto));
        registerRequestDto.setPassword(passwordHandler.encodePassword(registerRequestDto.getPassword()));
        User user = userRequestMapper.toUser(registerRequestDto);
        user.setRoleId(role.getId());
        userServicePort.saveUser(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(registerRequestDto.getEmail());
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        return authResponseMapper.toResponse(
                tokenHandler.createToken(userDetails.getUsername(), userDetails.getUsername(), roles)
        );
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        return null;
    }
}
