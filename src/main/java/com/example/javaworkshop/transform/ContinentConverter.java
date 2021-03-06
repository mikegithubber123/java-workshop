package com.example.javaworkshop.transform;

import com.example.javaworkshop.common.Continent;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ContinentConverter implements AttributeConverter<Continent, String> {

    @Override
    public String convertToDatabaseColumn(Continent attribute) {
        switch (attribute) {
            case ASIA:
                return Continent.ASIA.getValue();
            case EUROPE:
                return Continent.EUROPE.getValue();
            case NORTH_AMERICA:
                return Continent.NORTH_AMERICA.getValue();
            case AFRICA:
                return Continent.AFRICA.getValue();
            case OCEANIA:
                return Continent.OCEANIA.getValue();
            case ANTARCTICA:
                return Continent.ANTARCTICA.getValue();
            case SOUTH_AMERICA:
                return Continent.SOUTH_AMERICA.getValue();
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }

    @Override
    public Continent convertToEntityAttribute(String dbData) {
        switch (dbData) {
            case "Asia":
                return Continent.ASIA;
            case "Europe":
                return Continent.EUROPE;
            case "North America":
                return Continent.NORTH_AMERICA;
            case "Africa":
                return Continent.AFRICA;
            case "Oceania":
                return Continent.OCEANIA;
            case "Antarctica":
                return Continent.ANTARCTICA;
            case "South America":
                return Continent.SOUTH_AMERICA;
            default:
                throw new IllegalArgumentException("Unknown" + dbData);
        }
    }
}

