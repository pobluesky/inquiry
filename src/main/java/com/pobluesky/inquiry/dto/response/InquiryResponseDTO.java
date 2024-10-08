package com.pobluesky.inquiry.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pobluesky.feign.Customer;
import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Country;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import com.pobluesky.inquiry.entity.Progress;
import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record InquiryResponseDTO(
    Long inquiryId,
    Long userId,
    String name,
    String customerName,
    String customerCode,
    String email,
    String phone,
    Country country,
    String corporate,
    String  salesPerson,
    Manager salesManagerSummaryDto,
    Manager qualityManagerSummaryDto,
    InquiryType inquiryType,
    Industry industry,
    String corporationCode,
    ProductType productType,
    Progress progress,
    String customerRequestDate,
    String additionalRequests,
    String fileName,
    String filePath,
    String responseDeadline,
    List<LineItemResponseDTO> lineItemResponseDTOs,
    Boolean isActivated,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    LocalDateTime createdDate
) {

    public static InquiryResponseDTO of(
        Inquiry inquiry,
        List<LineItemResponseDTO> lineItemResponseDTOs,
        UserClient userClient
    ) {
        Customer customer = userClient.getCustomerByIdWithoutToken(inquiry.getUserId()).getData();
        Manager salesManager = null;
        Manager qualityManager = null;

        if(inquiry.getSalesManagerId()!=null){
            salesManager = userClient.getManagerByIdWithoutToken(inquiry.getSalesManagerId()).getData();
        }
        if(inquiry.getQualityManagerId()!=null){
            qualityManager = userClient.getManagerByIdWithoutToken(inquiry.getQualityManagerId()).getData();
        }

        return InquiryResponseDTO.builder()
            .inquiryId(inquiry.getInquiryId())
            .userId(inquiry.getUserId())
            .name(customer.getName())
            .customerName(customer.getCustomerName())
            .customerCode(customer.getCustomerCode())
            .corporationCode(inquiry.getCorporationCode())
            .email(customer.getEmail())
            .phone(customer.getPhone())
            .country(inquiry.getCountry())
            .corporate(inquiry.getCorporate())
            .salesPerson(inquiry.getSalesPerson())
            .salesManagerSummaryDto(salesManager)
            .qualityManagerSummaryDto(qualityManager)
            .inquiryType(inquiry.getInquiryType())
            .industry(inquiry.getIndustry())
            .corporationCode(inquiry.getCorporationCode())
            .productType(inquiry.getProductType())
            .progress(inquiry.getProgress())
            .customerRequestDate(inquiry.getCustomerRequestDate())
            .additionalRequests(inquiry.getAdditionalRequests())
            .fileName(inquiry.getFileName())
            .filePath(inquiry.getFilePath())
            .responseDeadline(inquiry.getResponseDeadline())
            .lineItemResponseDTOs(lineItemResponseDTOs)
            .isActivated(inquiry.getIsActivated())
            .createdDate(inquiry.getCreatedDate())
            .build();
    }
}
