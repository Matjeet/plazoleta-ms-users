package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.RoleDTO;
import com.pragma.powerup.application.dto.response.AuthResponseDto;
import com.pragma.powerup.application.mapper.IRoleDtoMapper;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IRoleDtoMapper.class})
public interface IUserResponseMapper {

    @Mapping(target = "role.name", source = "roleDTO.name")
    @Mapping(target = "name", source = "user.name")
    AuthResponseDto toResponse(User user, RoleDTO roleDTO);
}
