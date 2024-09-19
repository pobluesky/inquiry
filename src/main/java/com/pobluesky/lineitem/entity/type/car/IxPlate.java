package com.pobluesky.lineitem.entity.type.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IxPlate {

    DASH_PANEL("대쉬 패널"),
    FLOOR_PANEL("플로어 패널"),
    DOOR_PANEL("도어 패널"),
    TRUNK_LID("트렁크 리드");

    private String name;
}
