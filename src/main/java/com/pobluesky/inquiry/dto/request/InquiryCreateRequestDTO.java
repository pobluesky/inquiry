package com.pobluesky.inquiry.dto.request;


import com.pobluesky.inquiry.entity.Country;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import java.util.List;
import java.util.Map;

public record InquiryCreateRequestDTO(
    Country country,
    String corporate,
    String salesPerson,
    InquiryType inquiryType,
    Industry industry,
    ProductType productType,
    String customerRequestDate,
    String additionalRequests,
    String responseDeadline,
    List<Map<String, Object>> lineItemRequestDTOs
) {
    public Inquiry toInquiryEntity(String fileName, String filePath) {

        return Inquiry.builder()
            .country(country)
            .corporate(corporate)
            .salesPerson(salesPerson)
            .inquiryType(inquiryType)
            .industry(industry)
            .corporationCode("(주) 포스코")
            .productType(productType)
            .customerRequestDate(customerRequestDate)
            .additionalRequests(additionalRequests)
            .fileName(fileName)
            .filePath(filePath)
            .responseDeadline(responseDeadline)
            .build();
    }
}
