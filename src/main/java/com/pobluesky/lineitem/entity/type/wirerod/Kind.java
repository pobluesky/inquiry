package com.pobluesky.lineitem.entity.type.wirerod;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Kind {

    SWRH("일반 탄소강 선재"),
    SWRM("기계 구조용 선재"),
    SWRS("스프링용 선재");

    private String name;
}
