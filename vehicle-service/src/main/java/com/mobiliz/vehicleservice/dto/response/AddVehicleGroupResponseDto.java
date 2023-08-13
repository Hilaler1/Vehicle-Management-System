package com.mobiliz.vehicleservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddVehicleGroupResponseDto {
    private Long id;
    private String name;

}
