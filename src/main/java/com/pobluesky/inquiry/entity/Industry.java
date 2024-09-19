package com.pobluesky.inquiry.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Industry {

    AUTOMOBILE("Automobile"),
    OTHERS("Others"),
    CONSTRUCTION("Construction"),
    DISTRIBUTION("Distribution"),
    ELECTRIC("Electric"),
    FURNITURE("Furniture"),
    PLATING("Plating"),
    HIGH_CARBON("High-Carbon"),
    KITCHEN("Kitchen"),
    LOW_CARBON("Low-Carbon"),
    MACHINERY("Machinery"),
    PIPE("Pipe"),
    REROLLING("Rerolling"),
    SHIPBUILDING("Shipbuilding"),
    TRANSPORTATION("Transportation"),
    VESSEL("Vessel"),
    BEAM("Beam");

    private final String englishName;
}
