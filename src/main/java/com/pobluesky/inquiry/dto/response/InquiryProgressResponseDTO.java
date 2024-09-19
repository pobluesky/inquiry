package com.pobluesky.inquiry.dto.response;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.Progress;
import lombok.Builder;

@Builder
public record InquiryProgressResponseDTO(
    Long inquiryId,
    Progress progress
) {
    public static InquiryProgressResponseDTO from(Inquiry inquiry) {
        return InquiryProgressResponseDTO.builder()
            .inquiryId(inquiry.getInquiryId())
            .progress(inquiry.getProgress())
            .build();
    }
}
