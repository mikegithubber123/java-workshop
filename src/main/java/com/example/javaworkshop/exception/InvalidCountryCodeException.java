package com.example.javaworkshop.exception;

public class InvalidCountryCodeException extends RuntimeException {
    public InvalidCountryCodeException() {
        super("INVALID_COUNTRY_CODE");
    }
}
