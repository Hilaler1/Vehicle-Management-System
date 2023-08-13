package com.mobiliz.vehicleservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FilterVehicleIdsResponseDto {
    private List<Long> validVehicleIds;
}
