package com.example.javaworkshop.dto;

import com.example.javaworkshop.common.Continent;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel("country information")
@ToString
public class CountryDto {
    @Schema(description = "name of the country", example = "Bahrain")
    String name;
    @Schema(description = "Country continent",
            allowableValues = {"Asia",
                    "Europe",
                    "North America",
                    "Africa",
                    "Oceania",
                    "Antarctica",
                    "South America",})
    Continent continent;
    @Schema(description = "Country population", example = "617000")
    Integer population;
    @Schema(description = "life expectancy in the country", example = "73")
    @JsonProperty("life_expectancy")
    Integer lifeExpectancy;
    @Schema(description = "language spoken in the country", example = "Arabic")
    @JsonProperty("country_language")
    String countryLanguage;
}
