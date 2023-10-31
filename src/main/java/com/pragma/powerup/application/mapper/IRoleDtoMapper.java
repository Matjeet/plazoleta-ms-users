package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.RoleDTO;
import com.pragma.powerup.domain.model.Role;

public interface IRoleDtoMapper {

    RoleDTO toDto(Role role);
}
