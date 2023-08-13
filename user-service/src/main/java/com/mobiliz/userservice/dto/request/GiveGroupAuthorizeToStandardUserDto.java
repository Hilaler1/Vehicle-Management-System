package com.mobiliz.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiveGroupAuthorizeToStandardUserDto {

    private Long userId;
    private List<Long> groupIds;
}
