package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.response.AuthResponseDto;
import com.pragma.powerup.application.dto.response.UserInfoResponseDto;
import com.pragma.powerup.application.exception.UserIsNotAClientException;
import com.pragma.powerup.application.handler.IPasswordHandler;
import com.pragma.powerup.application.handler.ITokenHandler;
import com.pragma.powerup.application.mapper.request.IUserRequestMapper;
import com.pragma.powerup.application.mapper.response.IAuthResponseMapper;
import com.pragma.powerup.application.mapper.response.IUserResponseMapper;
import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.api.IValidatorServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest {

    private Role role;
    private User user;
    private RegisterRequestDto registerRequestDto;
    private AuthResponseDto authResponseDto;
    private UserInfoResponseDto userInfoResponseDto;
    @InjectMocks
    private UserHandler userHandler;
    @Mock
    private IRoleServicePort roleServicePort;
    @Mock
    private IUserRequestMapper userRequestMapper;
    @Mock
    private IPasswordHandler passwordHandler;
    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private IAuthResponseMapper authResponseMapper;
    @Mock
    private ITokenHandler tokenHandler;
    @Mock
    private IValidatorServicePort validatorServicePort;
    @Mock
    private SecurityContext securityContext;
    @Mock
    private Authentication authentication;
    @Mock
    private IUserResponseMapper userResponseMapper;
    @BeforeEach
    void setUp() {
        user = new User();
        role = new Role();
        registerRequestDto = new RegisterRequestDto();
        authResponseDto = new AuthResponseDto();
        userInfoResponseDto = new UserInfoResponseDto();

        user.setId(1);
        user.setName("Mateo");
        user.setLastName("Velásquez");
        user.setDocumentId("1000919492");
        user.setPhoneNumber("+573226749122");
        user.setBirthDate(LocalDate.of(2002,11,29));
        user.setEmail("matius29002@gmail.com");
        user.setPassword("1234");
        user.setRoleId(1);
        user.setRestaurantId(null);
    }

    @Test
    void saveUserSuccessRules() {

        role.setId(1);
        role.setName("administrador");

        registerRequestDto.setId("1");
        registerRequestDto.setName("Mateo");
        registerRequestDto.setLastName("Velásquez");
        registerRequestDto.setDocumentId("1000919492");
        registerRequestDto.setPhoneNumber("+573226749122");
        registerRequestDto.setBirthDate(LocalDate.of(2002,11,29));
        registerRequestDto.setEmail("matius29002@gmail.com");
        registerRequestDto.setPassword("1234");
        registerRequestDto.setRole(role);

        authResponseDto.setToken("awsdvbc");

        UserDetails userDetailsMock = mock(UserDetails.class);

        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //when(authentication.getAuthorities()).thenReturn(anyCollection());

        //SecurityContextHolder.setContext(securityContext);

        /*
        when(tokenHandler.getTokeRole()).thenReturn("ROLE_administrador");
        when(validatorServicePort.rolesValidator(
                "ROLE_administrador",
                registerRequestDto.getRole().getName()
            )
        ).thenReturn(true);
        when(validatorServicePort.ageValidator(registerRequestDto.getBirthDate())).thenReturn(true);
        when(userHandler.validateRules(
                "ROLE_administrador",
                "administrador",
                LocalDate.of(2002,11,29)
        )).thenReturn(true);
        when(userRequestMapper.toRole(registerRequestDto)).thenReturn(role);
        when(roleServicePort.saveRol(role)).thenReturn(role);
        when(passwordHandler.encodePassword(registerRequestDto.getPassword())).thenReturn(anyString());
        when(userRequestMapper.toUser(registerRequestDto)).thenReturn(user);
        doNothing().when(userServicePort).saveUser(user);
        when(userDetailsService.loadUserByUsername(registerRequestDto.getEmail()))
                .thenReturn(userDetailsMock);
        when(tokenHandler.createToken(
                anyString(),
                anyString(),
                anyList()
        )).thenReturn("awsdcv");
        when(authResponseMapper.toResponse(anyString())).thenReturn(authResponseDto);

        AuthResponseDto result = userHandler.saveUser(registerRequestDto);

        assertEquals("awsdvbc", result.getToken());

         */
        when(tokenHandler.getTokenRole()).thenReturn("ROLE_administrador");
        when(validatorServicePort.rolesValidator(anyString(),anyString())).thenReturn(true);
        when(validatorServicePort.ageValidator(any(LocalDate.class))).thenReturn(true);
        when(userHandler.validateRules(
                "ROLE_administrador",
                "administrador",
                LocalDate.of(2002,11,29)
        )).thenReturn(true);
        when(userRequestMapper.toRole(registerRequestDto)).thenReturn(new Role());
        doReturn(Role.class).when(roleServicePort).saveRol(new Role());
        when(passwordHandler.encodePassword(anyString())).thenReturn(anyString());
        when(userRequestMapper.toUser(new RegisterRequestDto())).thenReturn(new User());
        doNothing().when(userServicePort).saveUser(new User());
        when(userDetailsService.loadUserByUsername(anyString()))
                .thenReturn(userDetailsMock);
        when(tokenHandler.createToken(
                anyString(),
                anyString(),
                anyList()
        )).thenReturn("awserfdghvsdfguiuytfc");
        when(authResponseMapper.toResponse("awserfdghvsdfguiuytfc")).thenReturn(new AuthResponseDto());

        AuthResponseDto result = userHandler.saveUser(registerRequestDto);

        assertNotEquals(null, result);

    }

    @Test
    void login() {
    }

    @Test
    void validateRules() {
    }

    @Test
    void getTokeRole() {
    }

    @Test
    void getClientSuccess(){

        userInfoResponseDto.setId(1);
        userInfoResponseDto.setName("");
        userInfoResponseDto.setLastName("");
        userInfoResponseDto.setDocumentId("");
        userInfoResponseDto.setPhoneNumber("");
        userInfoResponseDto.setBirthDate(LocalDate.of(1,1,1));
        userInfoResponseDto.setEmail("");
        userInfoResponseDto.setRoleId(1);

        when(userServicePort.validateClientRole(anyInt())).thenReturn(true);
        when(userServicePort.getUser(anyInt())).thenReturn(user);
        when(userResponseMapper.toUserInfoDto(user)).thenReturn(userInfoResponseDto);

        UserInfoResponseDto result = userHandler.getClient(anyInt());

        verify(userServicePort,times(1)).validateClientRole(anyInt());
        verify(userServicePort,times(1)).getUser(anyInt());
        verify(userResponseMapper, times(1)).toUserInfoDto(user);
        assertInstanceOf(UserInfoResponseDto.class, result);
    }

    @Test
    void getClientFailed(){
        when(userServicePort.validateClientRole(anyInt())).thenReturn(false);

        UserIsNotAClientException exception = assertThrows(UserIsNotAClientException.class, () -> {
            userHandler.getClient(1);
        }, "No exception was made");

        assertNull(exception.getMessage());
    }
}