package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.Constants;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    @Override
    public void saveUser(User user) {
        if(
                userRepository.findByEmail(user.getEmail()).isPresent() ||
                userRepository.findByDocumentIdAndRoleId(
                        user.getDocumentId(), user.getRoleId()
                ).isPresent()
        ) {
            throw new UserAlreadyExistsException();
        }
        userRepository.save(
                userEntityMapper.toEntity(
                        user,
                        roleRepository.getById(user.getRoleId())
                )
        );
    }

    @Override
    public boolean validateOwnerRole(int id) {
        Optional<UserEntity> ownerInfo = userRepository.findById(id);
        if(ownerInfo.isPresent()){
            UserEntity owner = ownerInfo.get();
            return owner.getRole().getName().equals(Constants.OWNER);
        }
        return false;
    }

    @Override
    public boolean validateRestaurantEmployee(int idEmployee, int idRestaurant) {
        return false;
    }
}
