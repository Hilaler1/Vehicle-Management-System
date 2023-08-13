package com.mobiliz.userservice.service;

import com.mobiliz.userservice.config.JwtTokenManager;
import com.mobiliz.userservice.dto.request.*;
import com.mobiliz.userservice.dto.response.AddCompanyResponseDto;
import com.mobiliz.userservice.dto.response.FilterGroupIdsResponseDto;
import com.mobiliz.userservice.dto.response.FilterVehicleIdsResponseDto;
import com.mobiliz.userservice.entity.Company;
import com.mobiliz.userservice.entity.User;
import com.mobiliz.userservice.entity.enums.Role;
import com.mobiliz.userservice.exception.ErrorType;
import com.mobiliz.userservice.exception.UserServiceException;
import com.mobiliz.userservice.manager.VehicleGroupManager;
import com.mobiliz.userservice.manager.VehicleManager;
import com.mobiliz.userservice.repository.CompanyRepository;
import com.mobiliz.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final VehicleManager vehicleManager;
    private final VehicleGroupManager vehicleGroupManager;
    private final JwtTokenManager jwtTokenManager;



    public Boolean giveGroupAuthorizeToStandardUser(GiveGroupAuthorizeToStandardUserDto dto, String token) {
        User user= userService.findById(dto.getUserId());
        Optional<Long> companyId= jwtTokenManager.getCompanyId(token.substring(7));
        if(!Objects.equals(user.getCompany().getId(), companyId.get())){
            throw new UserServiceException(ErrorType.NOT_AUTHORIZED);
        }
        try {
            FilterGroupIdsResponseDto dto1 =
                    vehicleGroupManager.filterById(FilterGroupIdsRequestDto.builder().groupIds(dto.getGroupIds()).build(), token).getBody();
            if (user.getRole().equals(Role.STANDARDUSER)) {
                dto1.getValidGroupIds().stream().forEach(x -> {
                    user.getGroupIds().add(x);
                });
                userRepository.save(user);
                return true;
            } else {
                throw new UserServiceException(ErrorType.INVALID_ACCOUNT);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UserServiceException(ErrorType.EXCEPTION);
        }
    }
    public Boolean giveVehicleAuthorizeToStandardUser(GiveVehicleAuthorizeToStandardUserDto dto, String token) {
        User user= userService.findById(dto.getUserId());
        Optional<Long> companyId= jwtTokenManager.getCompanyId(token.substring(7));
        if(!Objects.equals(user.getCompany().getId(), companyId.get())){
            throw new UserServiceException(ErrorType.NOT_AUTHORIZED);
        }

        try {
            FilterVehicleIdsResponseDto dto1 =
                    vehicleManager.filterById(FilterVehicleIdsRequestDto.builder().vehicleIds(dto.getVehicleIds()).build(), token).getBody();
            if (user.getRole().equals(Role.STANDARDUSER)) {
                dto1.getValidVehicleIds().stream().forEach(x -> {
                    user.getVehicleIds().add(x);
                });
                userRepository.save(user);
                return true;
            } else {
                throw new UserServiceException(ErrorType.INVALID_ACCOUNT);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new UserServiceException(ErrorType.EXCEPTION);

        }
    }
    public AddCompanyResponseDto addCompany(AddCompanyRequestDto dto) {
        Company company = Company.builder()
                .companyName(dto.getName())
                .build();
        companyRepository.save(company);
        return AddCompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getCompanyName())
                .build();
    }
}





















