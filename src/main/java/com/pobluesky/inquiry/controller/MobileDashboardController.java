package com.pobluesky.inquiry.controller;

import com.pobluesky.inquiry.service.InquiryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile/api/inquiries/dashboard")
public class MobileDashboardController {

    private final InquiryService inquiryService;

    @Autowired
    public MobileDashboardController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/average-monthly")
    public ResponseEntity<Map<String, List<Object[]>>> getAverageMonthlyInquiry(@RequestHeader("Authorization") String token) {
        Map<String, List<Object[]>> response = inquiryService.getAverageDaysPerMonth(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/counts-by-progress")
    public ResponseEntity<Map<String, List<Object[]>>> getInquiryCountsByProgress(@RequestHeader("Authorization") String token) {
        Map<String, List<Object[]>> response = inquiryService.getInquiryCountsByProgress(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/percentage-completed-uncompleted")
    public ResponseEntity<Map<String, Map<String, String>>> getInquiryPercentageCompletedUncompleted(@RequestHeader("Authorization") String token) {
        Map<String, Map<String, String>> response = inquiryService.getInquiryPercentageCompletedUncompleted(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/counts-by-productType")
    public ResponseEntity<Map<String, List<Object[]>>> getInquiryCountsByProductType(@RequestHeader("Authorization") String token) {
        Map<String, List<Object[]>> response = inquiryService.getInquiryCountsByProductType(token);
        return ResponseEntity.ok(response);
    }
}