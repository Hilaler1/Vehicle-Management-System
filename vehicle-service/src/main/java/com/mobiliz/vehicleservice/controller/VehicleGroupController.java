package com.mobiliz.vehicleservice.controller;

import com.mobiliz.vehicleservice.dto.request.*;
import com.mobiliz.vehicleservice.dto.response.AddVehicleGroupResponseDto;
import com.mobiliz.vehicleservice.dto.response.FilterGroupIdsResponseDto;
import com.mobiliz.vehicleservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.vehicleservice.service.VehicleGroupService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/vehiclegroup")
@RequiredArgsConstructor
public class VehicleGroupController {
    private final VehicleGroupService vehicleGroupService;

    @PostMapping("/add")
    @Operation(summary = "araç grubu eklememizi sağlayan metot")
    public ResponseEntity<AddVehicleGroupResponseDto> addVehicleGroup(@RequestBody @Valid AddVehicleGroupRequestDto dto){
        return ResponseEntity.ok(vehicleGroupService.addVehicleGroup(dto));
    }

    @PostMapping("/addparentgroup")
    @Operation(summary = "id si girilen alt gruplara bir üst grup atama metodu")
    public ResponseEntity<AddVehicleGroupResponseDto> addParentGroup(@RequestBody AddParentGroupRequestDto dto){
        return ResponseEntity.ok(vehicleGroupService.addParentGroup(dto));
    }

    @PostMapping("/findById")
    @Operation(summary = "company tarafından bir kişiye grup yetkisi verilirken varolmayan bir" +
            " gruba yetki yapılamasın diye grup idlerini kontrol eden metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Long> findById(@RequestBody @Valid IdRequestDto dto){
        return ResponseEntity.ok(vehicleGroupService.findById(dto.getId()));
    }

    @PostMapping("/filterById")
    @Operation(summary = "list olarak gelen group id lerden geçerli olanları dönen metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<FilterGroupIdsResponseDto> filterById(@RequestBody @Valid FilterGroupIdsRequestDto dto,
                                                                @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(vehicleGroupService.filterById(dto));
    }

    @PostMapping("/authenticatedvehiclegroups")
    @PreAuthorize("hasAuthority('STANDARDUSER')")
    @Operation(summary = "standart user yetkilendiği araçları listeleyebliyor")
    public ResponseEntity<PlateNumberResponseDto> getAuthenticatedVehicleGroups(@RequestHeader(value = "Authorization") String token,
                                                                           @RequestBody AuthenticatedVehicleGroupIdsRequestDto dto){
        return ResponseEntity.ok(vehicleGroupService.getAuthenticatedVehicleGroups(dto,token));
    }






}

