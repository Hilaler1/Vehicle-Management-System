package com.mobiliz.userservice.controller;

import com.mobiliz.userservice.dto.request.AddCompanyRequestDto;
import com.mobiliz.userservice.dto.request.GiveGroupAuthorizeToStandardUserDto;
import com.mobiliz.userservice.dto.request.GiveVehicleAuthorizeToStandardUserDto;
import com.mobiliz.userservice.dto.request.IdRequestDto;
import com.mobiliz.userservice.dto.response.AddCompanyResponseDto;
import com.mobiliz.userservice.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('COMPANYADMIN')")
public class CompanyController {

    private  final CompanyService companyService;

    @PostMapping("/givegroupauthorize")
    @Operation(summary = "standart kullanıcılara grup yetkisi veren metot")
    public ResponseEntity<Boolean> giveGroupAuthorizeToStandardUser(@RequestBody @Valid GiveGroupAuthorizeToStandardUserDto dto,
                                                                    @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(companyService.giveGroupAuthorizeToStandardUser(dto,token));
    }

    @PostMapping("/givevehicleauthorize")
    @Operation(summary = "standart kullanıcılara vehicle yetkisi veren metot")
    public ResponseEntity<Boolean> giveVehicleAuthorizeToStandardUser(@RequestBody @Valid GiveVehicleAuthorizeToStandardUserDto dto,
                                                                      @RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(companyService.giveVehicleAuthorizeToStandardUser(dto,token));
    }

    @PostMapping("/add")
    @Operation(summary = "company ekleyen metot")
    public ResponseEntity<AddCompanyResponseDto> addCompany(@RequestBody @Valid AddCompanyRequestDto dto){
        return ResponseEntity.ok(companyService.addCompany(dto));
    }



}
