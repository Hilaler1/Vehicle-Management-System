package com.mobiliz.userservice.service;

import com.mobiliz.userservice.config.JwtTokenManager;
import com.mobiliz.userservice.dto.request.*;
import com.mobiliz.userservice.dto.response.CreateUserResponseDto;
import com.mobiliz.userservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.userservice.dto.response.TokenResponseDto;
import com.mobiliz.userservice.entity.Company;
import com.mobiliz.userservice.entity.User;
import com.mobiliz.userservice.entity.enums.Role;
import com.mobiliz.userservice.exception.ErrorType;
import com.mobiliz.userservice.exception.UserServiceException;
import com.mobiliz.userservice.manager.VehicleGroupManager;
import com.mobiliz.userservice.manager.VehicleManager;
import com.mobiliz.userservice.mapper.UserMapper;
import com.mobiliz.userservice.repository.CompanyRepository;
import com.mobiliz.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final CompanyRepository companyRepository;
    private final VehicleManager vehicleManager;
    private final VehicleGroupManager vehicleGroupManager;

    public CreateUserResponseDto createUser(CreateUserRequestDto dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        if(user.isPresent()){
            throw new UserServiceException(ErrorType.EMAIL_ADDRESS_ALREADY_EXIST);
        }
        Optional<Company> company= companyRepository.findById(dto.getCompanyId());
        if(company.isEmpty()){
            throw new UserServiceException(ErrorType.COMPANY_NOT_FOUND);
        }
        User standartUser= UserMapper.INSTANCE.fromCreateUserRequestDto(dto);
        standartUser.setRole(Role.STANDARDUSER);
        standartUser.setCompany(company.get());
        userRepository.save(standartUser);
        return UserMapper.INSTANCE.fromUser(standartUser);

    }

    public TokenResponseDto login(LoginRequestDto dto) {
        Optional<User> user = userRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(user.isEmpty()){
            throw new UserServiceException(ErrorType.CREDENTIALS_NOT_TRUE);
        }
        Optional<String> token = jwtTokenManager.createToken(user.get().getId(),
                String.valueOf(user.get().getRole()),user.get().getCompany().getId());
        if(token.isEmpty()){
            throw new UserServiceException(ErrorType.TOKEN_NOT_CREATED);
        }
        return TokenResponseDto.builder()
                .token(token.get())
                .build();
    }

    public CreateUserResponseDto grantRole(ChangeRoleRequestDto dto) {
        Optional<User> user= userRepository.findById(dto.getId());
        if(user.isEmpty()){
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
        user.get().setRole(Role.COMPANYADMIN);
        userRepository.save(user.get());
        return UserMapper.INSTANCE.fromUser(user.get());
    }
    public CreateUserResponseDto revokeRole(ChangeRoleRequestDto dto) {
        Optional<User> user= userRepository.findById(dto.getId());
        if(user.isEmpty()){
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
        user.get().setRole(Role.STANDARDUSER);
        userRepository.save(user.get());
        return UserMapper.INSTANCE.fromUser(user.get());
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
        return user.get();
    }


    public PlateNumberResponseDto authenticatedVehicles(String token) {
        Optional<Long> standardUserId= jwtTokenManager.getUserId(token.substring(7));
        Optional<User> user= userRepository.findById(standardUserId.get());
        if(user.isEmpty()){
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
        try {
            return vehicleManager.getAuthenticatedVehicles(token,
                    AuthenticatedVehicleIdsRequestDto.builder()
                            .vehicleIds(user.get().getVehicleIds()).build()).getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new UserServiceException(ErrorType.EXCEPTION);

        }


    }

    public PlateNumberResponseDto getAuthenticatedVehicleGroups(String token) {
        Optional<Long> standardUserId= jwtTokenManager.getUserId(token.substring(7));
        Optional<User> user= userRepository.findById(standardUserId.get());
        if(user.isEmpty()) {
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        }
        try {
            return vehicleGroupManager.getAuthenticatedVehicleGroups(token,
                    AuthenticatedVehicleGroupIdsRequestDto.builder()
                            .vehicleGroupIds(user.get().getGroupIds()).build()).getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new UserServiceException(ErrorType.EXCEPTION);
        }

    }

}
