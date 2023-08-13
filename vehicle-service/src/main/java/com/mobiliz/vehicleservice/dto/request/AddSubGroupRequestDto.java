package com.mobiliz.vehicleservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSubGroupRequestDto {
    private String name;
    private Long parentGroupId;
}
