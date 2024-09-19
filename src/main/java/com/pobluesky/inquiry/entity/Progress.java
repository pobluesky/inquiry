package com.pobluesky.inquiry.entity;

import lombok.Getter;

@Getter
public enum Progress {

    SUBMIT(1, "Submit","제출"),
    RECEIPT(2, "Receipt", "접수"),
    FIRST_REVIEW_COMPLETED(3, "First Review completed","1차검토완료"),
    QUALITY_REVIEW_REQUEST(4, "Quality Review request","품질검토요청"),
    QUALITY_REVIEW_RESPONSE(5, "Quality Review response","품질검토접수"),
    QUALITY_REVIEW_COMPLETED(6, "Quality Review completed","품질검토완료"),
    FINAL_REVIEW_COMPLETED(7, "Final Review completed","최종검토");

    private final Integer code;
    private final String value;
    private final String term;

    Progress(Integer code ,String value, String term) {
        this.code = code;
        this.value = value;
        this.term = term;
    }
}
