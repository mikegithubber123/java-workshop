package com.example.javaworkshop.model;


import com.example.javaworkshop.common.Continent;
import com.example.javaworkshop.transform.ContinentConverter;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("country representation")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country {
    @Id
    String code;
    String name;
    @Convert(converter = ContinentConverter.class)
    Continent continent;
    String region;
    String surfaceArea;
    String indepYear;
    Integer population;
    Integer lifeExpectancy;
    Integer gnp;
    Integer gnpOld;
    String localName;
    String governmentForm;
    String headOfState;
    Integer capital;
    String code2;
}
