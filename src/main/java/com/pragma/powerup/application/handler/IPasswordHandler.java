package com.pragma.powerup.application.handler;

public interface IPasswordHandler {

    String encodePassword(String password);

    boolean decodePassword(String originalPassword, String hashPassword);
}
