package com.example.javaworkshop.transform;

import com.example.javaworkshop.dto.CountryDto;
import com.example.javaworkshop.model.Country;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CountryMapper {
    public CountryDto map(Country input) {
        return CountryDto.builder()
                .continent(input.getContinent())
                .lifeExpectancy(input.getLifeExpectancy())
                .name(input.getName())
                .population(input.getPopulation())
                .build();
    }
}
