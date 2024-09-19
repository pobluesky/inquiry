package com.pobluesky.lineitem.dto.request.wirerod;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.WireRodLineItem;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.wirerod.Kind;

public record WireRodLineItemCreateRequestDTO(
    Kind kind,
    InqName inqName,
    String orderCategory,
    String diameter,
    Integer quantity,
    String expectedDeadline,
    Integer initialQuantity,
    String customerProcessing,
    String finalUse,
    String transportationDestination,
    String annualCost,
    String legalRegulatoryReview,
    String legalRegulatoryReviewDetail,
    String finalCustomer
) {

    public WireRodLineItem toWireRodLineItemEntity(Inquiry inquiry) {

        return WireRodLineItem.builder()
            .inquiry(inquiry)
            .kind(kind)
            .inqName(inqName)
            .orderCategory(orderCategory)
            .diameter(diameter)
            .quantity(quantity)
            .expectedDeadline(expectedDeadline)
            .initialQuantity(initialQuantity)
            .customerProcessing(customerProcessing)
            .finalUse(finalUse)
            .transportationDestination(transportationDestination)
            .annualCost(annualCost)
            .legalRegulatoryReview(legalRegulatoryReview)
            .legalRegulatoryReviewDetail(legalRegulatoryReviewDetail)
            .finalCustomer(finalCustomer)
            .build();

    }
}
