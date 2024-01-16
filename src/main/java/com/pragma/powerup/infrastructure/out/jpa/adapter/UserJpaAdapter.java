package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.Constants;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmployeeEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;
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

         UserEntity userEntity = userRepository.save(
                userEntityMapper.toEntity(
                        user,
                        roleRepository.getById(user.getRoleId())
                )
        );

        if (
                user.getRestaurantId() != null &&
                roleRepository.getReferenceById(user.getRoleId()).getName().equals("empleado")
        ){
            restaurantEmployeeRepository.save(
                    restaurantEmployeeEntityMapper.toRestaurantEmployeeEntity(
                            userEntity,
                            user.getRestaurantId()
                    )
            );
        }
        else {
            throw new IllegalArgumentException();
        }
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

        UserEntity userEntity = userRepository.getReferenceById(idEmployee);
        if (userEntity.getRole().getName().equals(Constants.EMPLOYEE)){
            RestaurantEmployeeEntity employeeEntity = restaurantEmployeeRepository.findByEmployee(userEntity);
            return employeeEntity.getRestaurantId() == idRestaurant;
        }
        return false;
    }
}
