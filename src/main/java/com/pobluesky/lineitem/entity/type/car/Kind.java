package com.pobluesky.lineitem.entity.type.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Kind {

    SEDAN("세단"),
    SUV("SUV"),
    TRUCK("트럭");

    private String name;
}
