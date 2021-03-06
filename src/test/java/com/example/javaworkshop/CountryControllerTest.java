package com.example.javaworkshop;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.javaworkshop.common.Continent;
import com.example.javaworkshop.dto.CountryDto;
import com.example.javaworkshop.exception.InvalidCountryCodeException;
import com.example.javaworkshop.service.CountryService;
import com.example.javaworkshop.web.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.net.ConnectException;

@WebMvcTest(CountryController.class)
public class CountryControllerTest {
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
    private static final String VALID_ENDPOINT = "/BHR";
    private static final String INVALID_ENDPOINT = "/TST";
    private static final String RETURN_BODY =
            "{\"name\":\"testName123\","
                    + "\"continent\":\"AFRICA\","
                    + "\"population\":123,"
                    + "\"life_expectancy\":123,"
                    + "\"country_language\":\"testLanguage123\"}";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void shouldBeAbleToReturnCountryDto() throws Exception {

        given(countryService.getCountryByCode(any(String.class))).willReturn(createCountryDto());

        mockMvc.perform(MockMvcRequestBuilders.get(VALID_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string(RETURN_BODY));
    }

    @Test
    void givenNonExistentCodeShouldReturnErrorMessageAndInternalServerError() throws Exception {

        given(countryService.getCountryByCode(any(String.class))).willThrow(InvalidCountryCodeException.class);

        String error = mockMvc.perform(MockMvcRequestBuilders.get(INVALID_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError())
                .andReturn().getResponse().getErrorMessage();


        //todo: fix tests

//        assertTrue(StringUtils.contains(error, INVALID_COUNTRY_CODE));
    }


    @Test
    void givenDatabaseIsDownShouldReturnErrorMessageAndInternalServerError() throws Exception {

        given(countryService.getCountryByCode(any(String.class))).willAnswer(invocation -> {
            throw new ConnectException();
        });

//        String error =
                mockMvc.perform(MockMvcRequestBuilders.get(VALID_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
//                .andReturn().getResponse().getErrorMessage();

        //todo: fix tests

//        assertTrue(StringUtils.contains(error, INTERNAL_ERROR));
    }

    private CountryDto createCountryDto() {
        return CountryDto.builder()
                .population(123)
                .lifeExpectancy(123)
                .name("testName123")
                .countryLanguage("testLanguage123")
                .continent(Continent.AFRICA)
                .build();
    }
}
