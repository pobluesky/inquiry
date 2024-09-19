package com.pobluesky.quality.dto.request;

public record QualityReviewInfoUpdateRequestDTO (
    String finalResult,
    String finalResultDetails,
    String standard,
    String orderCategory,
    String coatingMetalQuantity,
    String coatingOilQuantity,
    String thicknessTolerance,
    String orderEdge,
    String customerQReq,
    String availableLab
) {
}
