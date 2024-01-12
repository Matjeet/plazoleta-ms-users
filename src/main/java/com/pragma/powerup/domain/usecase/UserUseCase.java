package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.ParamaterNotValidException;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;


import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort iUserPersistencePort;


    public UserUseCase(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }

    @Override
    public void saveUser(User user) {

        boolean validEmail = Pattern.compile(
                "^(.+)@(\\S+)$",
                Pattern.CASE_INSENSITIVE
                )
                .matcher(user.getEmail())
                .find();

        boolean validPhoneNumber = Pattern.compile(
                "^\\+\\d{12}$",
                Pattern.CASE_INSENSITIVE
                )
                .matcher(user.getPhoneNumber())
                .find();

        boolean validDocumentId = Pattern.compile(
                "^\\d+$",
                Pattern.CASE_INSENSITIVE
        ).matcher(user.getDocumentId()).find();

        if(validEmail && validPhoneNumber && validDocumentId) {
            iUserPersistencePort.saveUser(user);
        }
        else{
            throw new ParamaterNotValidException();
        }
    }

    @Override
    public boolean validateOwnerRole(int id) {
        return iUserPersistencePort.validateOwnerRole(id);
    }
}
