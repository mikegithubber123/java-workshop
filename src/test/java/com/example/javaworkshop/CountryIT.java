package com.example.javaworkshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.javaworkshop.common.Continent;
import com.example.javaworkshop.dto.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryIT {

    private static final String LOCALHOST_URL = "http://localhost:";
    public static final String VALID_CODE = "BHR";
    public static final String INVALID_CODE = "TST";
    public static final String INVALID_COUNTRY_CODE = "INVALID_COUNTRY_CODE";

    @LocalServerPort
    private int applicationPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenValidCodeShouldBeAbleToGetCountryDtoByCode() {
        //when
        ResponseEntity<CountryDto> response = this.restTemplate.getForEntity(
                createCountryUri(VALID_CODE) , CountryDto.class);
        log.info("Response status: {}", response.getStatusCode());

        CountryDto returnDto = response.getBody();
        //then
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Continent.ASIA, returnDto.getContinent());

    }

    @Test
    void givenInvalidCodeShouldThrowInternalServerErrorWithResponseMessage() {
        //when
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                createCountryUri(INVALID_CODE) , String.class);
        log.info("Response status: {}", response.getStatusCode());
        //then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        assertEquals(INVALID_COUNTRY_CODE, response.getBody());
    }

    private URI createCountryUri(String code) {
        return UriComponentsBuilder.fromHttpUrl(CountryIT.LOCALHOST_URL)
                .pathSegment(code)
                .port(applicationPort)
                .build()
                .toUri();
    }
}
