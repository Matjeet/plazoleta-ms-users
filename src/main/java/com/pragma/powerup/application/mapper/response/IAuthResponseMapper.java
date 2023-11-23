package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.AuthResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthResponseMapper {

    AuthResponseDto toResponse(String token);
}