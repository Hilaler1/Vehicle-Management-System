package com.mobiliz.vehicleservice.exception;

import lombok.Getter;


@Getter
public class VehicleServiceException extends RuntimeException{

    private final ErrorType errorType;


    public VehicleServiceException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public VehicleServiceException(ErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }
}
