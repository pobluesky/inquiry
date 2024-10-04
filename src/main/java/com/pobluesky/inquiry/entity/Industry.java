package com.pobluesky.inquiry.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Industry {

    AUTOMOBILE("Automobile", "자동차"),
    OTHERS("Others", "기타"),
    CONSTRUCTION("Construction", "건설"),
    DISTRIBUTION("Distribution", "유통"),
    ELECTRIC("Electric", "전기"),
    FURNITURE("Furniture", "가구"),
    PLATING("Plating", "도금"),
    HIGH_CARBON("High-Carbon", "고탄소"),
    KITCHEN("Kitchen", "주방"),
    LOW_CARBON("Low-Carbon", "저탄소"),
    MACHINERY("Machinery", "기계"),
    PIPE("Pipe", "파이프"),
    REROLLING("Rerolling", "재압연"),
    SHIPBUILDING("Shipbuilding", "조선"),
    TRANSPORTATION("Transportation", "운송"),
    VESSEL("Vessel", "선박"),
    BEAM("Beam", "빔");


    private final String englishName;
    private final String koreanName;

    public static Industry fromKoreanName(String koreanName) {
        for (Industry industry : Industry.values()) {
            if (industry.getKoreanName().equals(koreanName))
                return industry;
        }

        throw new IllegalArgumentException("Invalid industry value: " + koreanName);
    }
}
