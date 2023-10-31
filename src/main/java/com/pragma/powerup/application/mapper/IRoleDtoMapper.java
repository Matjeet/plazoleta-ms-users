package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.RoleDTO;
import com.pragma.powerup.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleDtoMapper {

    RoleDTO toDto(Role role);
}
