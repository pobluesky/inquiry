package com.pobluesky.lineitem.dto.response.hotrolled;

import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;
import com.pobluesky.lineitem.entity.HotRolledLineItem;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.hotrolled.Kind;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HotRolledLineItemResponseDTO extends LineItemResponseDTO {

    private Long lineItemId;

    private Long inquiryId;

    private Kind kind;

    private InqName inqName;

    private String orderCategory;

    private String thickness;

    private String width;

    private String hardness;

    private String flatness;

    private String orderEdge;

    private Integer quantity;

    private Boolean isActivated;

    private String yieldingPoint;

    private String tensileStrength;

    private String elongationRatio;

    private String camber;

    private String annualCost;

    public static HotRolledLineItemResponseDTO from(HotRolledLineItem hotRolledLineItem) {

        return HotRolledLineItemResponseDTO.builder()
            .lineItemId(hotRolledLineItem.getLineItemId())
            .inquiryId(hotRolledLineItem.getInquiry().getInquiryId())
            .kind(hotRolledLineItem.getKind())
            .inqName(hotRolledLineItem.getInqName())
            .orderCategory(hotRolledLineItem.getOrderCategory())
            .thickness(hotRolledLineItem.getThickness())
            .width(hotRolledLineItem.getWidth())
            .hardness(hotRolledLineItem.getHardness())
            .flatness(hotRolledLineItem.getFlatness())
            .orderEdge(hotRolledLineItem.getOrderEdge())
            .quantity(hotRolledLineItem.getQuantity())
            .isActivated(hotRolledLineItem.getIsActivated())
            .yieldingPoint(hotRolledLineItem.getYieldingPoint())
            .tensileStrength(hotRolledLineItem.getTensileStrength())
            .elongationRatio(hotRolledLineItem.getElongationRatio())
            .camber(hotRolledLineItem.getCamber())
            .annualCost(hotRolledLineItem.getAnnualCost())
            .build();
    }
}
