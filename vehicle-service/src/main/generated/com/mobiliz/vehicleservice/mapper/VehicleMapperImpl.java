package com.mobiliz.vehicleservice.mapper;

import com.mobiliz.vehicleservice.dto.request.AddVehicleRequestDto;
import com.mobiliz.vehicleservice.entity.Vehicle;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-12T17:48:53+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle fromAddVehicleRequestDto(AddVehicleRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Vehicle.VehicleBuilder vehicle = Vehicle.builder();

        vehicle.plateNumber( dto.getPlateNumber() );
        vehicle.chassisNumber( dto.getChassisNumber() );
        vehicle.tag( dto.getTag() );
        vehicle.brand( dto.getBrand() );
        vehicle.model( dto.getModel() );
        vehicle.modelYear( dto.getModelYear() );

        return vehicle.build();
    }
}
