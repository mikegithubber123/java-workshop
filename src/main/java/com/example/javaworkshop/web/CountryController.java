package com.example.javaworkshop.web;

import com.example.javaworkshop.dto.CountryDto;
import com.example.javaworkshop.exception.InvalidCountryCodeException;
import com.example.javaworkshop.service.CountryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;


@Slf4j
@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @ApiOperation(value = "Receives country code and returns country information")
    @GetMapping("/{countryCode}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = void.class),
            @ApiResponse(code = 400, message = "Invalid parameters provided", response = void.class),
            @ApiResponse(code = 401, message = "Unauthorized operations of api call", response = void.class),
            @ApiResponse(code = 500, message = "Technical error", response = void.class)
    })
    public CountryDto getCountry(@PathVariable String countryCode) {

        log.info("App got request to find country by code: {}", countryCode);
        return countryService.getCountryByCode(countryCode);
    }


    @ExceptionHandler(InvalidCountryCodeException.class)
    public ResponseEntity<?> handleInvalidCountryCodeException(InvalidCountryCodeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<?> handleConnectException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_ERROR");
    }

//    private ResponseEntity<Error> createErrorResponse(WebRequest request,String message) {
//        Error error = Error.builder()
//                .code(500)
//                .message(message)
//                .build();
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
