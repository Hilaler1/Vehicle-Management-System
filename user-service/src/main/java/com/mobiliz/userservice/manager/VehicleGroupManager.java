package com.mobiliz.userservice.manager;

import com.mobiliz.userservice.dto.request.AuthenticatedVehicleGroupIdsRequestDto;
import com.mobiliz.userservice.dto.request.FilterGroupIdsRequestDto;
import com.mobiliz.userservice.dto.response.FilterGroupIdsResponseDto;
import com.mobiliz.userservice.dto.response.PlateNumberResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@FeignClient(name = "company-vehicle-group", url = "http://localhost:4040/vehiclegroup", decode404 = true)
public interface VehicleGroupManager {

    @PostMapping("/filterById")
    ResponseEntity<FilterGroupIdsResponseDto> filterById(@RequestBody @Valid FilterGroupIdsRequestDto dto,
                                                                @RequestHeader(value = "Authorization") String token);

    @PostMapping("/authenticatedvehiclegroups")
    ResponseEntity<PlateNumberResponseDto> getAuthenticatedVehicleGroups(@RequestHeader(value = "Authorization") String token,
                                                                                @RequestBody AuthenticatedVehicleGroupIdsRequestDto dto);

    }
