package com.mobiliz.vehicleservice.service;

import com.mobiliz.vehicleservice.config.JwtTokenManager;
import com.mobiliz.vehicleservice.dto.request.*;
import com.mobiliz.vehicleservice.dto.response.AddVehicleResponseDto;
import com.mobiliz.vehicleservice.dto.response.FilterVehicleIdsResponseDto;
import com.mobiliz.vehicleservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.vehicleservice.entity.Vehicle;
import com.mobiliz.vehicleservice.entity.enums.Status;
import com.mobiliz.vehicleservice.exception.ErrorType;
import com.mobiliz.vehicleservice.exception.VehicleServiceException;
import com.mobiliz.vehicleservice.mapper.VehicleMapper;
import com.mobiliz.vehicleservice.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleGroupService vehicleGroupService;
    private final JwtTokenManager jwtTokenManager;

    public AddVehicleResponseDto createVehicle(AddVehicleRequestDto dto, String token) {
        //todo burada şirket id si kontrolü yapmıyorum. olmayan bir şirket id si de girilebilir dikkat!
        Optional<Long> companyId= jwtTokenManager.getCompanyId(token.substring(7));
        Vehicle vehicle = VehicleMapper.INSTANCE.fromAddVehicleRequestDto(dto);
        vehicle.setVehicleGroup(vehicleGroupService.addVehicleToGroup(dto.getGroupId()));
        vehicle.setStatus(Status.ACTIVE);
        vehicle.setCompanyId(companyId.get());
        vehicleRepository.save(vehicle);
        return AddVehicleResponseDto.builder()
                .id(vehicle.getId())
                .plateNumber(vehicle.getPlateNumber())
                .brand(vehicle.getBrand())
                .build();

    }

    public PlateNumberResponseDto getAllVehiclesFromGroup(Long groupId,String token) {
        Optional<Long> companyId= jwtTokenManager.getCompanyId(token.substring(7));
        List<String> plateNumbers = vehicleRepository.findAllByGroupId(groupId,companyId.get());
        if(plateNumbers.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_GROUP_NOT_FOUND);
        }
        PlateNumberResponseDto dto = new PlateNumberResponseDto();
        dto.setPlateNumbers((Set<String>) plateNumbers);
        return dto;
    }


    public Boolean deleteVehicle(IdRequestDto dto) {
        Optional<Vehicle> vehicle= vehicleRepository.findById(dto.getId());
        if(vehicle.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_NOT_FOUND);
        }
        vehicle.get().setStatus(Status.DELETED);
        vehicleRepository.save(vehicle.get());
        return true;

    }

    public Boolean updatePlateNumber(UpdatePlateNumberRequestDto dto) {
        Optional<Vehicle> vehicle= vehicleRepository.findById(dto.getId());
        if(vehicle.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_NOT_FOUND);
        }
        vehicle.get().setPlateNumber(dto.getPlateNumber());
        vehicleRepository.save(vehicle.get());
        return true;
    }

    public Boolean updateCompanyId(UpdateCompanyIdRequestDto dto) {
        //todo burada şirket id si kontrolü yapmıyorum. olmayan bir şirket id si de girilebilir dikkat!
        Optional<Vehicle> vehicle= vehicleRepository.findById(dto.getId());
        if(vehicle.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_NOT_FOUND);
        }
        vehicle.get().setCompanyId(dto.getCompanyId());
        vehicleRepository.save(vehicle.get());
        return true;
    }

    public FilterVehicleIdsResponseDto filterById(FilterVehicleIdsRequestDto dto) {
        List<Long> ids = vehicleRepository.getAllIds();
        List<Long> filteredList=dto.getVehicleIds().stream().filter(x-> ids.contains(x)).collect(Collectors.toList());
        if(filteredList.isEmpty()){
            throw new VehicleServiceException(ErrorType.VEHICLE_NOT_FOUND);
        }
        return FilterVehicleIdsResponseDto.builder().validVehicleIds(filteredList).build();
    }

    public PlateNumberResponseDto getAuthanticatedVehicles(AuthenticatedVehicleIdsRequestDto dto) {
        return PlateNumberResponseDto.builder()
                .plateNumbers(vehicleRepository.getPlateNumbersByIdInAndStatusNot(dto.getVehicleIds()))
                .build();

    }
}
