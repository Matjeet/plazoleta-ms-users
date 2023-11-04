package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRoleServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.spi.IRolePersistencePort;

import java.util.List;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort irolePersistencePort;

    public RoleUseCase(IRolePersistencePort irolePersistencePort) {
        this.irolePersistencePort = irolePersistencePort;
    }

    @Override
    public Role saveRol(Role rol) {
        Role role = irolePersistencePort.saveRol(rol);
        return role;
    }

    @Override
    public int getRoleId(String name) {
        return irolePersistencePort.getRoleId(name);
    }


}
