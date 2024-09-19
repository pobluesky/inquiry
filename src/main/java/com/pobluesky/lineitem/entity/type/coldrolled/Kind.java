package com.pobluesky.lineitem.entity.type.coldrolled;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Kind {

    CR("일반 냉연 강판"),
    CRC("냉연 코일"),
    CRCA("냉연 완전 소둔강판");

    private String name;
}
