package com.pobluesky.inquiry.controller;

import com.pobluesky.inquiry.dto.response.MobileInquiryResponseDTO;
import com.pobluesky.inquiry.dto.response.MobileInquirySummaryResponseDTO;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import com.pobluesky.inquiry.entity.Progress;
import com.pobluesky.inquiry.service.InquiryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mobile/api/inquiries")
public class MobileInquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    public List<MobileInquirySummaryResponseDTO> getAllInquiries() {

        return inquiryService.getAllInquiries();
    }

    @GetMapping("/{inquiryId}")
    public MobileInquiryResponseDTO getInquiryById(@PathVariable Long inquiryId) {

        return inquiryService.getInquiryById(inquiryId);
    }

    @GetMapping("/search")
    public List<MobileInquirySummaryResponseDTO> getInquiriesBySearch(
            @RequestParam(defaultValue = "LATEST") String sortBy,
            @RequestParam(required = false) String progress,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String inquiryType,
            @RequestParam(required = false) String salesPerson,
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam(required = false) String salesManagerName,
            @RequestParam(required = false) String qualityManagerName
    ) {
        Progress progressEnum =  (progress != null) ? Progress.fromKoreanName(progress) : null;
        ProductType productTypeEnum = (productType != null) ? ProductType.fromKoreanName(productType) : null;
        InquiryType inquiryTypeEnum = (inquiryType != null) ? InquiryType.fromKoreanName(inquiryType): null;
        Industry industryEnum = (industry != null) ?  Industry.fromKoreanName(industry) : null;


        return inquiryService.getInquiriesBySearch(
                sortBy,
                progressEnum,
                productTypeEnum,
                customerName,
                inquiryTypeEnum,
                salesPerson,
                industryEnum,
                startDate,
                endDate,
                salesManagerName,
                qualityManagerName
        );
    }
}