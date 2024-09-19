package com.pobluesky.lineitem.dto.request.wirerod;

import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.wirerod.Kind;

public record WireRodLineItemUpdateRequestDTO(
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
}
