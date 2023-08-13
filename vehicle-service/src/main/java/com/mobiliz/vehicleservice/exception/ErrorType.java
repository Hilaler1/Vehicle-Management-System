package com.mobiliz.vehicleservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
    public enum ErrorType {

        INTERNAL_ERROR_SERVER(5100,"Sunucu Hatası",HttpStatus.INTERNAL_SERVER_ERROR),
        BAD_REQUEST(4100,"Parametre Hatası",HttpStatus.BAD_REQUEST),

        VEHICLE_GROUP_NOT_FOUND(200,"grup bulunamadı!",HttpStatus.BAD_REQUEST ),
        VEHICLE_NOT_FOUND(201,"araç bulunamadı!",HttpStatus.BAD_REQUEST ),
        INVALID_TOKEN(202,"Geçersiz token!" ,HttpStatus.BAD_REQUEST ),
        INVALID_GROUP_ID(203,"Alt grup bulunamadı veya geçersiz grup ID'leri var!" ,HttpStatus.BAD_REQUEST );

        private  int code;
        private String message;
        HttpStatus httpStatus;
}
