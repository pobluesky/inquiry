package com.pobluesky.lineitem.entity.type.hotrolled;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Kind {

    HR("일반 열연 강판"),
    HRC("열연 코일"),
    HRPO("산세 처리 열연 강판");

    private String name;
}
