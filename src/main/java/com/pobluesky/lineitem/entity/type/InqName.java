package com.pobluesky.lineitem.entity.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InqName {

    JS_SI123("JS-SI123"),
    JS_SI456("JS-SI456"),
    JS_SI789("JS-SI789"),
    JS_SI321("JS-SI321"),
    JS_SI654("JS-SI654"),
    JS_SI987("JS-SI987");

    private String name;
}