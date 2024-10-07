package com.pobluesky.inquiry.dto.response;

import com.pobluesky.feign.Customer;
import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Country;
import com.pobluesky.inquiry.entity.Inquiry;

import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.Builder;

@Builder
public record MobileInquiryResponseDTO(
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
    String managerName,
    String managerDepartment,
    String inquiryType,
    String industry,
    String corporationCode,
    String productType,
    String progress,
    String customerRequestDate,
    String additionalRequests,
    String fileName,
    String filePath,
    String responseDeadline,
    List<LineItemResponseDTO> lineItemResponseDTOs,
    String customInquiryId,
    Boolean isActivated
) {

    public static MobileInquiryResponseDTO of(
        Inquiry inquiry,
        List<LineItemResponseDTO> lineItemResponseDTOs,
        UserClient userClient
    ) {
        Customer customer = userClient.getCustomerByIdWithoutToken(inquiry.getUserId()).getData();
        Manager salesManager = null;
        String ManagerName=null;
        String ManagerDepartment=null;

        if(inquiry.getSalesManagerId()!=null){
            salesManager = userClient.getManagerByIdWithoutToken(inquiry.getSalesManagerId()).getData();
            ManagerName = salesManager.getName();
            ManagerDepartment = salesManager.getDepartment().toString();
        }


        return MobileInquiryResponseDTO.builder()
            .inquiryId(inquiry.getInquiryId())
            .userId(inquiry.getUserId())
            .name(customer.getName())
            .customerName(customer.getCustomerName())
            .customerCode(customer.getCustomerCode())
            .email(customer.getEmail())
            .phone(customer.getPhone())
            .country(inquiry.getCountry())
            .corporate(inquiry.getCorporate())
            .salesPerson(inquiry.getSalesPerson())
            .managerName(ManagerName)
            .managerDepartment(ManagerDepartment)
            .inquiryType(inquiry.getInquiryType().getKoreanName())
            .industry(inquiry.getIndustry().getKoreanName())
            .corporationCode(inquiry.getCorporationCode())
            .productType(inquiry.getProductType().getKoreanName())
            .progress(inquiry.getProgress().getKoreanName())
            .customerRequestDate(inquiry.getCustomerRequestDate())
            .additionalRequests(inquiry.getAdditionalRequests())
            .fileName(inquiry.getFileName())
            .filePath(inquiry.getFilePath())
            .responseDeadline(inquiry.getResponseDeadline())
            .lineItemResponseDTOs(lineItemResponseDTOs)
            .customInquiryId(generateCustomInquiryId(inquiry.getCreatedDate(), inquiry.getInquiryId()))
            .isActivated(inquiry.getIsActivated())
            .build();
    }

    static String generateCustomInquiryId(LocalDateTime createdDate, Long inquiryId) {
        String createdDateString = createdDate.toString();

        return "#" + createdDateString.split("T")[0].replace("-", "") + "P" + inquiryId;
    }
}
