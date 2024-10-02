package com.pobluesky.review.controller;

import com.pobluesky.review.dto.response.ReviewResponseDTO;
import com.pobluesky.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mobile/api/reviews/{inquiryId}")
public class MobileReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ReviewResponseDTO getReviewByInquiry(@PathVariable Long inquiryId) {

        return reviewService.getReviewByInquiry(inquiryId);
    }
}
