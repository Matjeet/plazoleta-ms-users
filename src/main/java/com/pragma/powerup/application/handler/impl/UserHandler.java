package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.response.AuthResponseDto;
import com.pragma.powerup.application.dto.response.UserInfoResponseDto;
import com.pragma.powerup.application.exception.UserIsNotAClientException;
import com.pragma.powerup.application.handler.IPasswordHandler;
import com.pragma.powerup.application.handler.ITokenHandler;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IRoleDtoMapper;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IAuthResponseMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.Constants;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.api.IValidatorServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    private final IValidatorServicePort validatorServicePort;
    private final IUserResponseMapper userResponseMapper;

    private static final String TOKEN_ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    private static final String TOKEN_EMPTY = "";
    private final IRoleDtoMapper roleDtoMapper;
    @Override
    public AuthResponseDto saveUser(RegisterRequestDto registerRequestDto) {

        String tokenRole = tokenHandler.getTokenRole();

        if (
                tokenRole.equals(TOKEN_ROLE_ANONYMOUS) &&
                        registerRequestDto.getRole().getName().equals("cliente") ||
                validateRules(
                        tokenRole,
                        registerRequestDto.getRole().getName(),
                        registerRequestDto.getBirthDate()
                )
        ) {
            Role role = roleServicePort.saveRol(userRequestMapper.toRole(registerRequestDto));
            registerRequestDto.setPassword(
                    passwordHandler.encodePassword(registerRequestDto.getPassword())
            );
            User user = userRequestMapper.toUser(registerRequestDto);
            user.setRoleId(role.getId());
            userServicePort.saveUser(user);

            UserDetails userDetails = userDetailsService.loadUserByUsername(
                    registerRequestDto.getEmail()
            );
            List<String> roles = userDetails
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return authResponseMapper.toResponse(
                    tokenHandler.createToken(
                            userDetails.getUsername(), userDetails.getUsername(), roles
                    )
            );
        }
        return authResponseMapper.toResponse(TOKEN_EMPTY);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {

        UserDetails userDetails;

        try{
            userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        }
        catch (UsernameNotFoundException e) {
            return null;
        }

        if(passwordHandler.decodePassword(loginRequestDto.getPassword(), userDetails.getPassword()))
        {
            List<String> roles = userDetails
                    .getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return authResponseMapper.toResponse(
                    tokenHandler.createToken(
                            userDetails.getUsername(), userDetails.getUsername(), roles
                    )
            );
        }
        return null;
    }

    @Override
    public boolean validateOwnerRole(int id) {
        return userServicePort.validateOwnerRole(id);
    }

    @Override
    public boolean validateRestaurantEmployee(int idEmployee, int idRestaurant) {
        return userServicePort.validateRestaurantEmployee(idEmployee, idRestaurant);
    }

    @Override
    public UserInfoResponseDto getClient(int idClient) {
        if(userServicePort.validateClientRole(idClient)){
            return userResponseMapper.toUserInfoDto(userServicePort.getUser(idClient));
        }
        else {
            throw new UserIsNotAClientException();
        }
    }


    public boolean validateRules(String tokenRole, String requestRole, LocalDate birthDate){
        if(!validatorServicePort.rolesValidator(tokenRole, requestRole))
        {
            return false;
        }
        if(requestRole.equals(Constants.REGISTER_ROLE_OWNER) &&
                !validatorServicePort.ageValidator(birthDate))
        {
            return false;
        }
        return true;
    }

}
