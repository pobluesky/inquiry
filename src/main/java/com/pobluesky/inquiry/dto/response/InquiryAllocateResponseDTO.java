package com.pobluesky.inquiry.dto.response;

import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Inquiry;
import lombok.Builder;

@Builder
public record InquiryAllocateResponseDTO (
    Long inquiryId,
    Manager salesManagerSummaryDto,
    Manager qualityManagerSummaryDto
) {
        public static InquiryAllocateResponseDTO from(Inquiry inquiry, UserClient userClient) {
            Manager salesManager = null;
            Manager qualityManager = null;

            if(inquiry.getSalesManagerId()!=null){
                salesManager = userClient.getManagerByIdWithoutToken(inquiry.getSalesManagerId()).getData();
            }
            if(inquiry.getQualityManagerId()!=null){
                qualityManager = userClient.getManagerByIdWithoutToken(inquiry.getQualityManagerId()).getData();
            }

            return InquiryAllocateResponseDTO.builder()
                .inquiryId(inquiry.getInquiryId())
                .salesManagerSummaryDto(salesManager)
                .qualityManagerSummaryDto(qualityManager)
                .build();
        }
}
