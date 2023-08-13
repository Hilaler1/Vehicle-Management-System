package com.mobiliz.vehicleservice.service;

import com.mobiliz.vehicleservice.config.JwtTokenManager;
import com.mobiliz.vehicleservice.dto.request.*;
import com.mobiliz.vehicleservice.dto.response.AddVehicleGroupResponseDto;
import com.mobiliz.vehicleservice.dto.response.FilterGroupIdsResponseDto;
import com.mobiliz.vehicleservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.vehicleservice.entity.VehicleGroup;
import com.mobiliz.vehicleservice.exception.ErrorType;
import com.mobiliz.vehicleservice.exception.VehicleServiceException;
import com.mobiliz.vehicleservice.repository.VehicleGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleGroupService {

    private final VehicleGroupRepository vehicleGroupRepository;
    private final JwtTokenManager jwtTokenManager;

    public AddVehicleGroupResponseDto addVehicleGroup(AddVehicleGroupRequestDto dto) {
        VehicleGroup vehicleGroup = vehicleGroupRepository.save(VehicleGroup.builder()
                .name(dto.getName())
                .build());
        return AddVehicleGroupResponseDto.builder()
                .id(vehicleGroup.getId())
                .name(vehicleGroup.getName())
                .build();
    }

    public AddVehicleGroupResponseDto addParentGroup(AddParentGroupRequestDto dto) {

        List<Optional<VehicleGroup>> subGroups = dto.getSubGroupIds().stream()
                .map(x -> vehicleGroupRepository.findById(x))
                .collect(Collectors.toList());

        if (subGroups.isEmpty() || subGroups.stream().anyMatch(Optional::isEmpty)) {
            throw new VehicleServiceException(ErrorType.INVALID_GROUP_ID);
        }
        VehicleGroup vehicleGroup = VehicleGroup.builder().name(dto.getName()).build();
        vehicleGroupRepository.save(vehicleGroup);


        subGroups.stream()
                .map(Optional::get)
                .forEach(group -> {
                    group.setParentGroup(vehicleGroup);
                    vehicleGroupRepository.save(group);
                });

        return AddVehicleGroupResponseDto.builder().name(vehicleGroup.getName()).id(vehicleGroup.getId()).build();
    }

    public VehicleGroup addVehicleToGroup(Long vehicleGroupId) {

        Optional<VehicleGroup> vehicleGroup = vehicleGroupRepository.findById(vehicleGroupId);

        if (vehicleGroup.isEmpty()) {
            throw new VehicleServiceException(ErrorType.VEHICLE_GROUP_NOT_FOUND);
        }
        return vehicleGroup.get();

    }

    public Long findById(Long id){
        Optional<VehicleGroup> vehicleGroup= vehicleGroupRepository.findById(id);
        if(vehicleGroup.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_GROUP_NOT_FOUND);
        }
        return id;
    }

    public FilterGroupIdsResponseDto filterById(FilterGroupIdsRequestDto dto) {
        List<Long> ids = vehicleGroupRepository.getAllIds();
        List<Long> filteredList=dto.getGroupIds().stream().filter(x-> ids.contains(x)).collect(Collectors.toList());
        if(filteredList.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_GROUP_NOT_FOUND);
        }
        return FilterGroupIdsResponseDto.builder().validGroupIds(filteredList).build();
    }

    public PlateNumberResponseDto getAuthenticatedVehicleGroups(AuthenticatedVehicleGroupIdsRequestDto dto, String token) {
        Optional<Long> companyId= jwtTokenManager.getCompanyId(token.substring(7));
        if(companyId.isEmpty()){
            throw new VehicleServiceException(ErrorType.INVALID_TOKEN);
        }
        return PlateNumberResponseDto.builder()
                .plateNumbers(vehicleGroupRepository.getAuthenticatedVehicleGroups(dto.getVehicleGroupIds(),companyId.get()))
                .build();
    }
}
