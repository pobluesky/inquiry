package com.pobluesky.review.controller;

import com.pobluesky.global.util.ResponseFactory;
import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.review.dto.request.ReviewCreateRequestDTO;
import com.pobluesky.review.dto.request.ReviewUpdateRequestDTO;
import com.pobluesky.review.dto.response.ReviewResponseDTO;
import com.pobluesky.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews/{inquiryId}")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "1차 검토 조회")
    @GetMapping
    public ResponseEntity<JsonResult> getReviewByInquiry(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        ReviewResponseDTO response = reviewService.getReviewByInquiry(token, inquiryId);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }
  
    @Operation(summary = "1차 검토 생성", description = "판매 담당자는 해당 Inquiry에 대한 1차 검토를 시작한다.")
    @PostMapping
    public ResponseEntity<JsonResult> createReview(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @RequestBody ReviewCreateRequestDTO request
        ) {
        ReviewResponseDTO response = reviewService.createReview(
            token,
            inquiryId,
            request
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @Operation(summary = "검토 수정", description = "판매 담당자는 해당 Inquiry에 대해 최종검토완료 버튼으로 review 종료")
    @PutMapping
    public ResponseEntity<JsonResult> updateReview(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @RequestBody ReviewUpdateRequestDTO request
    ) {
        ReviewResponseDTO response = reviewService.updateReview(
            token,
            inquiryId,
            request
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }
}
