package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum ProductType {

    COLD_ROLLED("Cold Rolled", "냉연"),
    HOT_ROLLED("Hot Rolled", "열연"),
    WIRE_ROD("Wire Rod", "선재"),
    THICK_PLATE("Thick Plate", "후판"),
    CAR("Car", "자동차");

    private final String englishName;
    private final String koreanName;

    ProductType(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }

    public static ProductType fromKoreanName(String koreanName) {
        for (ProductType productType : ProductType.values()) {
            if (productType.getKoreanName().equals(koreanName))
                return productType;
        }

        throw new IllegalArgumentException("Invalid productType value: " + koreanName);
    }
}
