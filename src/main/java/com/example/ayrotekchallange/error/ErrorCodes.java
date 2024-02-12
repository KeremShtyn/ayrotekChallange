package com.example.ayrotekchallange.error;


import com.example.ayrotekchallange.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum ErrorCodes implements ErrorCode {

    SYSTEM_FAILURE(-1, "ErrorCodes.SYSTEM_FAILURE", HttpStatus.INTERNAL_SERVER_ERROR),
    DATA_NOT_FOUND(100, "ErrorCodes.DATA_NOT_FOUND", HttpStatus.NOT_FOUND),
    PRODUCT_DATA_CAN_NOT_BE_EMPTY(101,"ErrorCodes.PRODUCT_DATA_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_CAN_NOT_BE_EMPTY(101,"ErrorCodes.PRODUCT_NAME_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_CAN_NOT_BE_EMPTY(101,"ErrorCodes.PRODUCT_PRICE_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    PRODUCT_DESCRIPTION_CAN_NOT_BE_EMPTY(101,"ErrorCodes.PRODUCT_DESCRIPTION_CAN_NOT_BE_EMPTY", HttpStatus.BAD_REQUEST),
    CLIENT_NAME_CAN_NOT_BE_EMPTY(101,"ErrorCodes.CLIENT_NAME_CAN_NOT_BE_EMPTY" , HttpStatus.BAD_REQUEST),
    CLIENT_NAME_NOT_UNIQUE(409, "ErrorCodes.CLIENT_NAME_NOT_UNIQUE", HttpStatus.CONFLICT),
    PRODUCT_NOT_BELONG_TO_CLIENT(404, "ErrorCodes.PRODUCT_NOT_BELONG_TO_CLIENT", HttpStatus.NOT_FOUND)
    ;

    private ErrorCodes(Integer code, String langKey, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.langKey = langKey;
    }


    private Integer code;

    private String langKey;

    private HttpStatus httpStatus;

    public Integer getCode() {
        return code;
    }

    public String getLangKey() {
        return langKey;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * @param code
     * @return
     */
    public ErrorCodes findByCode(Integer code) {
        return Arrays.asList(ErrorCodes.values()).stream().filter(f -> f.getCode().equals(code)).findFirst().orElse(ErrorCodes.SYSTEM_FAILURE);
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String langKey() {
        return this.langKey;
    }
}
