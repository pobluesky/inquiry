package com.pobluesky.inquiry.dto.response;

import com.pobluesky.feign.Customer;
import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Inquiry;
import lombok.Builder;

@Builder
public record MobileInquirySummaryResponseDTO (
    Long inquiryId,

    String progress,  //진행현황 e.g. 접수 -> 1차검토 -> ..

    String inquiryType, //유형 e.g 품질문의, 공통(견적/품질문의)

    String customerName, //고객사명 e.g. AAT

    String productType
) {
    public static MobileInquirySummaryResponseDTO from(
        Inquiry inquiry,
        UserClient userClient) {
        Customer customer = userClient.getCustomerByIdWithoutToken(inquiry.getUserId()).getData();

        return MobileInquirySummaryResponseDTO.builder()
                .inquiryId(inquiry.getInquiryId())
                .progress(inquiry.getProgress().getTerm())
                .inquiryType(inquiry.getInquiryType().getKoreanName())
                .customerName(customer.getCustomerName())
                .productType(inquiry.getProductType().toString())
                .build();
    }
}
