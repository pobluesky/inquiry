package com.pobluesky.inquiry.dto.response;

import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Inquiry;
import lombok.Builder;

@Builder
public record InquiryAllocateResponseDTO (
    Long inquiryId,
    ManagerSummaryResponseDTO salesManagerSummaryDto,
    ManagerSummaryResponseDTO qualityManagerSummaryDto
) {
        public static InquiryAllocateResponseDTO from(Inquiry inquiry, UserClient userClient) {
            ManagerSummaryResponseDTO salesManager = null;
            ManagerSummaryResponseDTO qualityManager = null;

            if(inquiry.getSalesManagerId()!=null){
                salesManager = userClient.getManagerSummaryById(inquiry.getSalesManagerId()).getData();
            }
            if(inquiry.getQualityManagerId()!=null){
                qualityManager = userClient.getManagerSummaryById(inquiry.getQualityManagerId()).getData();
            }

            return InquiryAllocateResponseDTO.builder()
                .inquiryId(inquiry.getInquiryId())
                .salesManagerSummaryDto(salesManager)
                .qualityManagerSummaryDto(qualityManager)
                .build();
        }
}
