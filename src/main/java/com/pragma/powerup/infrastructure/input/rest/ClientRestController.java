package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.response.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserRestController {

    public ResponseEntity<UserInfoResponseDto> getUser(@PathVariable idUser)
}
