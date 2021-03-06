package com.example.javaworkshop.model;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ApiModel("language of the country representation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryLanguage {
    @EmbeddedId
    CountryLanguageId countryLanguageId;
    boolean isOfficial;
    Integer percentage;
}
