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
    public Role saveRol(Role rol) {
        return roleEntityMapper.toRole(roleRepository.save(roleEntityMapper.toEntity(rol)));
    }

    @Override
    public int getRoleId(String name) {
        return 0;
    }
}
