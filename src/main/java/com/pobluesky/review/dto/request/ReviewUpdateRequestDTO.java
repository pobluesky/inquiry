package com.pobluesky.review.dto.request;

public record ReviewUpdateRequestDTO (
    SalesInfoDTO salesInfo,
    String reviewText,
    String finalReviewText,
    String tsReviewReq
){

}
