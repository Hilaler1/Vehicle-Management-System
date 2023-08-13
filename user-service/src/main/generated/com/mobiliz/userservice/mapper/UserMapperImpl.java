package com.mobiliz.userservice.mapper;

import com.mobiliz.userservice.dto.request.CreateUserRequestDto;
import com.mobiliz.userservice.dto.response.CreateUserResponseDto;
import com.mobiliz.userservice.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-10T21:53:40+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromCreateUserRequestDto(CreateUserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( dto.getName() );
        user.lastName( dto.getLastName() );
        user.email( dto.getEmail() );
        user.password( dto.getPassword() );

        return user.build();
    }

    @Override
    public CreateUserResponseDto fromUser(User user) {
        if ( user == null ) {
            return null;
        }

        CreateUserResponseDto.CreateUserResponseDtoBuilder createUserResponseDto = CreateUserResponseDto.builder();

        createUserResponseDto.name( user.getName() );
        createUserResponseDto.lastName( user.getLastName() );

        return createUserResponseDto.build();
    }
}
