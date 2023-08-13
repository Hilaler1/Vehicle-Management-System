package com.mobiliz.vehicleservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddVehicleResponseDto {
    private Long id;
    private String plateNumber;
    private String brand;
}
