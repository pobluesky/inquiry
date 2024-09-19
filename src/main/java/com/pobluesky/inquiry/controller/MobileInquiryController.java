package com.pobluesky.inquiry.controller;

import com.pobluesky.inquiry.dto.response.MobileInquiryResponseDTO;
import com.pobluesky.inquiry.dto.response.MobileInquirySummaryResponseDTO;
import com.pobluesky.inquiry.service.InquiryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
