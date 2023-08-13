package com.mobiliz.vehicleservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddVehicleRequestDto {

    private String plateNumber;
    private String chassisNumber;
    private String tag;
    private String brand;
    private String model;
    private int modelYear;
    private Long groupId;
}
