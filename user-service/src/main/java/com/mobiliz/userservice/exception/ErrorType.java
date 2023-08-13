package com.mobiliz.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR_SERVER(5100, "Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4100, "Parametre Hatası", HttpStatus.BAD_REQUEST),

    INVALID_ACCOUNT(100,"company admin e yetki verilemez!", HttpStatus.FORBIDDEN),
    EMAIL_ADDRESS_ALREADY_EXIST(101, "Bu mail adresi kullanılıyor!", HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(102, "Boyle bir kullanıcı bulunamadı!", HttpStatus.NOT_FOUND),
    CREDENTIALS_NOT_TRUE(103, "Giriş bilgileri hatalı!", HttpStatus.NOT_FOUND),
    TOKEN_NOT_CREATED(104, "Token olusturlamadı", HttpStatus.BAD_REQUEST),
    NOT_AUTHORIZED(105,"Bu kullanıcıyı yetkilendirme yetkiniz yok!" ,HttpStatus.FORBIDDEN),
    EXCEPTION(106,"Araçlar listelenirken bir hata oluştu" ,HttpStatus.METHOD_NOT_ALLOWED),
    USER_NOT_FOUND(107, "Boyle bir kullanıcı bulunamadı!", HttpStatus.NOT_FOUND);


    private int code;
    private String message;
    HttpStatus httpStatus;
}
