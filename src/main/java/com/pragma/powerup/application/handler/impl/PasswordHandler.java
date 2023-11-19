package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.handler.IPasswordHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class PasswordHandler implements IPasswordHandler {
    @Override
    public String encodePassword(String password) {
       return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public boolean decodePassword(String originalPassword, String hashPassword) {
        return false;
    }
}
