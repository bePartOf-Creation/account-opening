package com.accountopeningapplication.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    SUCCESS("Success", "00"),
    FAILED("Failed", "99"),
    INVALID_REQUEST("Invalid request", "MwG00"),
    GENERAL_ERROR("An error occurred", "MwG01"),
    ENCRYPTION_ERROR("Encryption error", "MwG02"),
    INVALID_FIELDS_PROVIDED("Invalid fields provided", "MwG03"),
    USER_NOT_FOUND("No user found with provided credentials", "MwG04"),
    USER_ALREADY_EXIST("User already exists", "MwG05"),
    INVALID_DATE_FORMAT("Invalid date format", "MwG06"),
    THIRD_PARTY_ERROR("Error: Third-party API exception. Please try again later.", "MwG07"),
    RECORD_NOT_FOUND("Record Not Found", "MwG10"),
    ;


    private final String status;
    private final String code;

    ResponseStatus(String status, String code) {
        this.status = status;
        this.code = code;
    }

}
