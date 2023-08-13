package com.mobiliz.vehicleservice.controller;

import com.mobiliz.vehicleservice.dto.request.*;
import com.mobiliz.vehicleservice.dto.response.AddVehicleResponseDto;
import com.mobiliz.vehicleservice.dto.response.FilterVehicleIdsResponseDto;
import com.mobiliz.vehicleservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.vehicleservice.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;


    @PostMapping("/create")
    @Operation(summary = "araç eklememizi sağlayan metot. aynı zamanda aracı gruba da ekliyor")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<AddVehicleResponseDto> createVehicle(@RequestBody @Valid AddVehicleRequestDto dto,
                                                               @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(vehicleService.createVehicle(dto, token));
    }

    @PostMapping("/getall")
    @Operation(summary = "grubun id sini girdiğimizde altındaki statusu deleted olmayan tüm araçların plakalarını gösteren metot.")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<PlateNumberResponseDto> getAllVehiclesFromGroup(@RequestBody IdRequestDto dto,
                                                                          @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(vehicleService.getAllVehiclesFromGroup(dto.getId(),token));
   }

   @DeleteMapping("/delete")
   @Operation(summary = "id si girilen aracı soft delete eden metot")
   @PreAuthorize("hasAuthority('COMPANYADMIN')")
   public ResponseEntity<Boolean> deleteVehicle(@RequestBody IdRequestDto dto){
        return ResponseEntity.ok(vehicleService.deleteVehicle(dto));
   }

    @PutMapping("/updateplatenumber")
    @Operation(summary = "aracın plakasını değiştiren metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Boolean> updatePlateNumber(@RequestBody UpdatePlateNumberRequestDto dto ){
        return ResponseEntity.ok(vehicleService.updatePlateNumber(dto));
   }

    @PutMapping("/updatecompanyıd")
    @Operation(summary = "aracın şirket id sini değiştiren metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<Boolean> updateCompanyId(@RequestBody UpdateCompanyIdRequestDto dto){
        return ResponseEntity.ok(vehicleService.updateCompanyId(dto));
    }

    @PostMapping("/filterById")
    @Operation(summary = "company tarafından bir kişiye araç yetkisi verilirken varolmayan bir araç yetkisi yapılamasın" +
            " diye araç idlerini kontrol eden metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<FilterVehicleIdsResponseDto> filterById(@RequestBody @Valid FilterVehicleIdsRequestDto dto,
                                                                  @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(vehicleService.filterById(dto));
    }

    @PostMapping("/authanticatedvehicles")
    @PreAuthorize("hasAuthority('STANDARDUSER')")
    @Operation(summary = "standart user yetkilendiği araçları listeleyebliyor")
    public ResponseEntity<PlateNumberResponseDto> getAuthanticatedVehicles(@RequestHeader(value = "Authorization") String token,
                                                                           @RequestBody AuthenticatedVehicleIdsRequestDto dto){
        return ResponseEntity.ok(vehicleService.getAuthanticatedVehicles(dto));
    }



}
