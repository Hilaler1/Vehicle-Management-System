package com.mobiliz.userservice.controller;

import com.mobiliz.userservice.dto.request.*;
import com.mobiliz.userservice.dto.response.AuthanticatedVehicleIdsResponseDto;
import com.mobiliz.userservice.dto.response.CreateUserResponseDto;
import com.mobiliz.userservice.dto.response.PlateNumberResponseDto;
import com.mobiliz.userservice.dto.response.TokenResponseDto;
import com.mobiliz.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("create")
    @Operation(summary = "standart kullanıcı kayıt eden metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserRequestDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
    @PostMapping("/login")
    @Operation(summary = "login olunca token dönen metot")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userService.login(dto));
    }
    @PostMapping ("/grantrole")
    @Operation(summary = "standart kullanıcının rolünü company admine çeken metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<CreateUserResponseDto> grantRole(@RequestBody ChangeRoleRequestDto dto){
        return ResponseEntity.ok(userService.grantRole(dto));
    }
    @PostMapping ("/revokerole")
    @Operation(summary = "company admin kullanıcının rolünü standartusera çeken metot")
    @PreAuthorize("hasAuthority('COMPANYADMIN')")
    public ResponseEntity<CreateUserResponseDto> revokeRole(@RequestBody ChangeRoleRequestDto dto){
        return ResponseEntity.ok(userService.revokeRole(dto));
    }

    @PostMapping("/authenticatedvehicles")
    @Operation(summary = "bir userın yetkilendirildiği tüm araçları listeleyen metot")
    @PreAuthorize("hasAuthority('STANDARDUSER')")
    public ResponseEntity<PlateNumberResponseDto> authenticatedVehicles(@RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(userService.authenticatedVehicles(token));
    }

    @PostMapping("/authenticatedgroups")
    @Operation(summary = "standart user a yetkili olduğu tüm grup ve alt gruptaki vehicleları gösteren metot")
    @PreAuthorize("hasAuthority('STANDARDUSER')")
    public ResponseEntity<PlateNumberResponseDto> getAuthenticatedVehicleGroups(@RequestHeader(value = "Authorization") String token){
        return ResponseEntity.ok(userService.getAuthenticatedVehicleGroups(token));
    }


}
