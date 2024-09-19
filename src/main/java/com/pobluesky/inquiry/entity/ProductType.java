package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum ProductType {

    COLD_ROLLED("Cold Rolled"),
    HOT_ROLLED("Hot Rolled"),
    WIRE_ROD("Wire Rod"),
    THICK_PLATE("Thick Plate"),
    CAR("Car");

    private final String type;

    ProductType(String type) {
        this.type = type;
    }
}
