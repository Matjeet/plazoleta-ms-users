package com.pragma.powerup.application.mapper.request;

import com.pragma.powerup.application.dto.request.UserRequestDTO;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResquestMapper {
    User toUser(UserRequestDTO userResquestDTO);

    @Mapping(source = "userResquestDTO.role.name", target = "name")
    Role toRole(UserRequestDTO userRequestDTO);

}
