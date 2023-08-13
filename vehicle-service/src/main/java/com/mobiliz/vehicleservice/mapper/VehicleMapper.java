package com.mobiliz.vehicleservice.mapper;

import com.mobiliz.vehicleservice.dto.request.AddVehicleRequestDto;
import com.mobiliz.vehicleservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.vehicleservice.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {


    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle fromAddVehicleRequestDto(final AddVehicleRequestDto dto);

}
