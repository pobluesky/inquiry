package com.pobluesky.quality.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class QualityReviewInfo {
    @Column(columnDefinition = "TEXT", nullable = false)
    private String finalResult; // 종합결과

    @Column(columnDefinition = "TEXT")
    private String finalResultDetails; // 종합결과세부사항

    private String standard; // 제품규격

    private String orderCategory; // 주문용도

    private String coatingMetalQuantity; // 도금량(코드)

    private String coatingOilQuantity; // 도유량(코드)

    private String thicknessTolerance; // 두께공차

    private String orderEdge; // 주문 edge

    private String customerQReq; // 고객품질요구사항

    private String availableLab; // 생산가능소구분

    private String fileName;

    private String filePath;

    /*
  Builder Pattern
 */
    @Builder
    private QualityReviewInfo(
        String finalResult,
        String finalResultDetails,
        String standard,
        String orderCategory,
        String coatingMetalQuantity,
        String coatingOilQuantity,
        String thicknessTolerance,
        String orderEdge,
        String customerQReq,
        String availableLab,
        String fileName,
        String filePath
    ) {
        this.finalResult = finalResult;
        this.finalResultDetails = finalResultDetails;
        this.standard = standard;
        this.orderCategory = orderCategory;
        this.coatingMetalQuantity = coatingMetalQuantity;
        this.coatingOilQuantity = coatingOilQuantity;
        this.thicknessTolerance = thicknessTolerance;
        this.orderEdge = orderEdge;
        this.customerQReq = customerQReq;
        this.availableLab = availableLab;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}

