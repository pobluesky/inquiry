package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum InquiryType {

    QUOTE_INQUIRY("Quote Inquiry", "견적문의"),
    COMMON_INQUIRY("General (Quote/Quality Inquiry)", "품질/견적문의");

    private final String englishName;
    private final String koreanName;

    InquiryType(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }

    public static InquiryType fromKoreanName(String koreanName) {
        for (InquiryType inquiryType : InquiryType.values()) {
            if (inquiryType.getKoreanName().equals(koreanName))
                return inquiryType;
        }

        throw new IllegalArgumentException("Invalid inquiryType value: " + koreanName);
    }
}
