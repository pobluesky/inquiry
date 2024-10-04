package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum Progress {

    SUBMIT("Submit","제출"),
    RECEIPT("Receipt", "접수"),
    FIRST_REVIEW_COMPLETED("First Review completed","1차검토완료"),
    QUALITY_REVIEW_REQUEST("Quality Review request","품질검토요청"),
    QUALITY_REVIEW_RESPONSE("Quality Review response","품질검토접수"),
    QUALITY_REVIEW_COMPLETED("Quality Review completed","품질검토완료"),
    FINAL_REVIEW_COMPLETED("Final Review completed","최종검토");

    private final String englishName;
    private final String koreanName;

    Progress(String englishName, String koreanName) {
        this.englishName = englishName;
        this.koreanName = koreanName;
    }

    public static Progress fromKoreanName(String koreanName) {
        for (Progress progress : Progress.values()) {
            if (progress.getKoreanName().equals(koreanName))
                return progress;
        }

        throw new IllegalArgumentException("Invalid progress value: " + koreanName);
    }
}
