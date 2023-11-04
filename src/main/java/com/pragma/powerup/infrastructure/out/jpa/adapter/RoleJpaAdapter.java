package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleEntityMapper roleEntityMapper;
    private  final IRoleRepository roleRepository;
    @Override
    public Role saveRol(Role role) {
        if (roleRepository.findByName(roleEntityMapper.toEntity(role).getName()).isPresent()){
            return roleEntityMapper.toRole(roleRepository.getByName(role.getName()));
        }
        return roleEntityMapper.toRole(roleRepository.save(roleEntityMapper.toEntity(role)));
    }

    @Override
    public int getRoleId(String name) {
        return 0;
    }
}
