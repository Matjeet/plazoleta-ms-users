package com.pragma.powerup.application.mapper.response;

import com.pragma.powerup.application.dto.response.UserInfoResponseDto;
import com.pragma.powerup.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    UserInfoResponseDto toUserInfoDto(User user);
}
