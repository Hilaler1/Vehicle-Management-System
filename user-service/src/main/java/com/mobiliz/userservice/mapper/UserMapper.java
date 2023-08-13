package com.mobiliz.userservice.mapper;

import com.mobiliz.userservice.dto.request.CreateUserRequestDto;
import com.mobiliz.userservice.dto.response.CreateUserResponseDto;
import com.mobiliz.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromCreateUserRequestDto(final CreateUserRequestDto dto);
    CreateUserResponseDto fromUser(final User user);




}
