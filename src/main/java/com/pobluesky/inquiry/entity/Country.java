package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum Country {

    USA("United States"),
    CANADA("Canada"),
    KOREA("South Korea"),
    JAPAN("Japan"),
    CHINA("China"),
    GERMANY("Germany"),
    FRANCE("France");

    private final String countryName;

    Country(String countryName) {
        this.countryName = countryName;
    }
}
