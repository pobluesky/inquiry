package com.pobluesky.quality.dto.request;

import com.pobluesky.quality.entity.QualityReviewInfo;

public record QualityReviewInfoCreateRequestDTO(
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
    // dto -> entity
    public QualityReviewInfo toQualityReviewInfoEntity(String fileName, String filePath) {
        return QualityReviewInfo.builder()
            .finalResult(finalResult)
            .finalResultDetails(finalResultDetails)
            .standard(standard)
            .orderCategory(orderCategory)
            .coatingMetalQuantity(coatingMetalQuantity)
            .coatingOilQuantity(coatingOilQuantity)
            .thicknessTolerance(thicknessTolerance)
            .orderEdge(orderEdge)
            .customerQReq(customerQReq)
            .availableLab(availableLab)
            .fileName(fileName)
            .filePath(filePath)
            .build();
    }
}
