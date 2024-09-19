package com.pobluesky.inquiry.dto.response;


import com.pobluesky.feign.Customer;
import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Country;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import com.pobluesky.inquiry.entity.Progress;
import lombok.Builder;

@Builder
public record InquirySummaryResponseDTO(
    Long inquiryId,
    String salesPerson, //판매 계약자 e.g. 현대종합상사(주)
    Progress progress,  //진행현황 e.g. 접수 -> 1차검토 -> ..
    ProductType productType, //제품구분 e.g. 자동차, 열연, ..
    InquiryType inquiryType, //유형 e.g 품질문의, 공통(견적/품질문의)
    String customerName,  //고객사명 e.g. AAT
    Country country, //국가 e.g. USA
    String corporate, //판매 상사 e.g. POA
    String corporationCode, //법인 코드
    Industry industry, //산업 분류 e.g. AUTOMOBILE
    String salesManagerName,
    String qualityManagerName
) {

    public static InquirySummaryResponseDTO from(Inquiry inquiry, UserClient userClient) {
        Customer customer = userClient.getCustomerByIdWithoutToken(inquiry.getInquiryId()).getData();
        ManagerSummaryResponseDTO salesManager = null;
        ManagerSummaryResponseDTO qualityManager = null;
        String salesManagerName = null;
        String qualityManagerName = null;

        if(inquiry.getSalesManagerId()!=null){
            salesManager = userClient.getManagerSummaryById(inquiry.getSalesManagerId()).getData();
            salesManagerName=salesManager.name();
        }
        if(inquiry.getQualityManagerId()!=null){
            qualityManager = userClient.getManagerSummaryById(inquiry.getQualityManagerId()).getData();
            qualityManagerName=qualityManager.name();
        }

        return InquirySummaryResponseDTO.builder()
            .inquiryId(inquiry.getInquiryId())
            .salesPerson(inquiry.getSalesPerson())
            .progress(inquiry.getProgress())
            .productType(inquiry.getProductType())
            .inquiryType(inquiry.getInquiryType())
            .customerName(customer.getCustomerName())
            .country(inquiry.getCountry())
            .corporate(inquiry.getCorporate())
            .corporationCode(inquiry.getCorporationCode())
            .industry(inquiry.getIndustry())
            .salesManagerName(
                salesManagerName
            )
            .qualityManagerName(
                qualityManagerName
            )
            .build();
    }
}
