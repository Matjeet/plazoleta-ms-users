package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.Rol;
import com.pragma.powerup.domain.spi.IRolPersistencePort;

public class RolUseCase implements IRolServicePort {

    private final IRolPersistencePort irolPersistencePort;

    public RolUseCase(IRolPersistencePort irolPersistencePort) {
        this.irolPersistencePort = irolPersistencePort;
    }

    @Override
    public void saveRol(Rol rol) {
        irolPersistencePort.saveRol(rol);
    }
}
