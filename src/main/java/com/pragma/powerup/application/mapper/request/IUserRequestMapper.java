package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {

    @Mapping(source = "userRequestDTO.role.name", target = "name")
    @Mapping(source = "userRequestDTO.role.id", target = "id")
    Role toRole(UserRequestDTO userRequestDTO);

    @Mapping(source = "userRequestDTO.role.id", target = "roleId")
    User toUser(UserRequestDTO userRequestDTO);

}
