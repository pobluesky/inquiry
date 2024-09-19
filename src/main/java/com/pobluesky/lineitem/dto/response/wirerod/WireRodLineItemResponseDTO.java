package com.pobluesky.lineitem.dto.response.wirerod;

import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;
import com.pobluesky.lineitem.entity.WireRodLineItem;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.wirerod.Kind;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WireRodLineItemResponseDTO extends LineItemResponseDTO {

    private Long lineItemId;

    private Long inquiryId;

    private Kind kind;

    private InqName inqName;

    private String orderCategory;

    private String diameter;

    private Integer quantity;

    private String expectedDeadline;

    private Integer initialQuantity;

    private String customerProcessing;

    private String finalUse;

    private Boolean isActivated;

    private String transportationDestination;

    private String annualCost;

    private String legalRegulatoryReview;

    private String legalRegulatoryReviewDetail;

    private String finalCustomer;

    public static WireRodLineItemResponseDTO from(WireRodLineItem wireRodLineItem) {

        return WireRodLineItemResponseDTO.builder()
            .lineItemId(wireRodLineItem.getLineItemId())
            .inquiryId(wireRodLineItem.getInquiry().getInquiryId())
            .kind(wireRodLineItem.getKind())
            .inqName(wireRodLineItem.getInqName())
            .orderCategory(wireRodLineItem.getOrderCategory())
            .diameter(wireRodLineItem.getDiameter())
            .quantity(wireRodLineItem.getQuantity())
            .expectedDeadline(wireRodLineItem.getExpectedDeadline())
            .initialQuantity(wireRodLineItem.getInitialQuantity())
            .customerProcessing(wireRodLineItem.getCustomerProcessing())
            .finalUse(wireRodLineItem.getFinalUse())
            .isActivated(wireRodLineItem.getIsActivated())
            .transportationDestination(wireRodLineItem.getTransportationDestination())
            .annualCost(wireRodLineItem.getAnnualCost())
            .legalRegulatoryReview(wireRodLineItem.getLegalRegulatoryReview())
            .legalRegulatoryReviewDetail(wireRodLineItem.getLegalRegulatoryReviewDetail())
            .finalCustomer(wireRodLineItem.getFinalCustomer())
            .build();
    }
}
