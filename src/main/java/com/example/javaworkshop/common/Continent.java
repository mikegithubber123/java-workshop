package com.example.javaworkshop.common;

import lombok.Getter;

@Getter
public enum Continent {
    ASIA("Asia"),
    EUROPE("Europe"),
    NORTH_AMERICA("North America"),
    AFRICA("Africa"),
    OCEANIA("Oceania"),
    ANTARCTICA("Antarctica"),
    SOUTH_AMERICA("South America");

    private String value;

    Continent(String value) {
        this.value = value;
    }
}
