package com.pobluesky.quality.dto.response;

import com.pobluesky.quality.entity.QualityReviewInfo;
import lombok.Builder;

@Builder
public record QualityReviewInfoResponseDTO (
    Long qualityReviewInfoId,
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
    // entity -> dto
    public static QualityReviewInfoResponseDTO from(QualityReviewInfo qualityReviewInfo) {
        return QualityReviewInfoResponseDTO.builder()
            .finalResult(qualityReviewInfo.getFinalResult())
            .finalResultDetails(qualityReviewInfo.getFinalResultDetails())
            .standard(qualityReviewInfo.getStandard())
            .orderCategory(qualityReviewInfo.getOrderCategory())
            .coatingMetalQuantity(qualityReviewInfo.getCoatingMetalQuantity())
            .coatingOilQuantity(qualityReviewInfo.getCoatingOilQuantity())
            .thicknessTolerance(qualityReviewInfo.getThicknessTolerance())
            .orderEdge(qualityReviewInfo.getOrderEdge())
            .customerQReq(qualityReviewInfo.getCustomerQReq())
            .availableLab(qualityReviewInfo.getAvailableLab())
            .build();
    }
}