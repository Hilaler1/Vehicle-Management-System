package com.mobiliz.vehicleservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticatedVehicleGroupIdsRequestDto {

    private Set<Long>vehicleGroupIds;
}
