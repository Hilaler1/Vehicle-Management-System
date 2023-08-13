package com.mobiliz.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequestDto {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Long companyId;

}
