package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.handler.ITokenHandler;

public class TokenHandler implements ITokenHandler {

    private final String ACCESS_TOKEN_SECRET = "fa52f4e13bc9557eb253c02ed25d0ecaf9c37a3e";
    private final int ACCESS_TOKEN_VALIDITY_SECONDS = 10800;
    @Override
    public String createToken() {
        return null;
    }
}
