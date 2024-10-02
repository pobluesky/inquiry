package com.pobluesky.review.service;

import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.global.error.CommonException;
import com.pobluesky.global.error.ErrorCode;
import com.pobluesky.global.security.UserRole;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.repository.InquiryRepository;
import com.pobluesky.review.dto.request.ReviewCreateRequestDTO;
import com.pobluesky.review.dto.request.ReviewUpdateRequestDTO;
import com.pobluesky.review.dto.response.ReviewResponseDTO;
import com.pobluesky.review.entity.Review;
import com.pobluesky.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserClient userClient;

    private final InquiryRepository inquiryRepository;


    @Transactional(readOnly = true)
    public ReviewResponseDTO getReviewByInquiry(String token, Long inquiryId){
        Long userId = userClient.parseToken(token);

        if(!userClient.managerExists(userId) && !userClient.customerExists(userId)){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        Review review = reviewRepository.findByInquiry(inquiry)
            .orElseThrow(() -> new CommonException(ErrorCode.REVIEW_NOT_FOUND));

        return ReviewResponseDTO.from(review);
    }

    @Transactional
    public ReviewResponseDTO createReview(
        String token,
        Long inquiryId,
        ReviewCreateRequestDTO dto
        ) {
        Manager manager = validateManager(token);

        if(manager.getRole() != UserRole.SALES)
            throw new CommonException(ErrorCode.USER_NOT_MATCHED);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        if(reviewRepository.existsByInquiry(inquiry)) {
            throw new CommonException(ErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Review review = dto.toReviewEntity(inquiry);
        Review savedReview = reviewRepository.save(review);

        return ReviewResponseDTO.from(savedReview);
    }

    @Transactional
    public ReviewResponseDTO updateReview(
        String token,
        Long inquiryId,
        ReviewUpdateRequestDTO request) {
        Manager manager = validateManager(token);

        if(manager.getRole() != UserRole.SALES)
            throw new CommonException(ErrorCode.USER_NOT_MATCHED);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        Review review = reviewRepository.findByInquiry(inquiry)
            .orElseThrow(() -> new CommonException(ErrorCode.REVIEW_NOT_FOUND));

        review.updateReview(request.finalReviewText());

        return ReviewResponseDTO.from(review);
    }

    private Manager validateManager(String token) {
        Long userId = userClient.parseToken(token);

        Manager manager = userClient.getManagerByIdWithoutToken(userId).getData();

        if(manager == null){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        return manager;
    }

    // 모바일 검토 조회
    @Transactional(readOnly = true)
    public ReviewResponseDTO getReviewByInquiry(Long inquiryId){
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        Review review = reviewRepository.findByInquiry(inquiry)
            .orElseThrow(() -> new CommonException(ErrorCode.REVIEW_NOT_FOUND));

        return ReviewResponseDTO.from(review);
    }
}
