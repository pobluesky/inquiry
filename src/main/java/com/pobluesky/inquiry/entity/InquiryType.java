package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum InquiryType {

    QUOTE_INQUIRY("Quote Inquiry", "견적문의"),
    COMMON_INQUIRY("General (Quote/Quality Inquiry)", "품질/견적문의");

    private final String type;
    private final String name;

    InquiryType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
