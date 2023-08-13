package com.mobiliz.userservice.exception;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class ErrorMessage{
    private int code;
    private String message;
    private List<String> fields;
    @Builder.Default
    private LocalDateTime date=LocalDateTime.now();
}
