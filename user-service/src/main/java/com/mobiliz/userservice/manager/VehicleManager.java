package com.mobiliz.userservice.manager;

import com.mobiliz.userservice.dto.request.AuthenticatedVehicleIdsRequestDto;
import com.mobiliz.userservice.dto.request.FilterVehicleIdsRequestDto;
import com.mobiliz.userservice.dto.response.FilterVehicleIdsResponseDto;
import com.mobiliz.userservice.dto.response.PlateNumberResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;

@FeignClient(name = "company-vehicle", url = "http://localhost:4040/vehicle", decode404 = true)
public interface VehicleManager {

    @PostMapping("/filterById")
    ResponseEntity<FilterVehicleIdsResponseDto> filterById(@RequestBody @Valid FilterVehicleIdsRequestDto dto,
                                                           @RequestHeader(value = "Authorization") String token);

    @PostMapping("/authanticatedvehicles")
    public ResponseEntity<PlateNumberResponseDto> getAuthenticatedVehicles(@RequestHeader(value = "Authorization") String token,
                                                                           @RequestBody AuthenticatedVehicleIdsRequestDto dto);
}
