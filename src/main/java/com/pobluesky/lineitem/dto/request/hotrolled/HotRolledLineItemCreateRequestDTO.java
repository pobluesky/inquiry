package com.pobluesky.lineitem.dto.request.hotrolled;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.HotRolledLineItem;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.hotrolled.Kind;

public record HotRolledLineItemCreateRequestDTO(
    Kind kind,
    InqName inqName,
    String orderCategory,
    String thickness,
    String width,
    String hardness,
    String flatness,
    String orderEdge,
    Integer quantity,
    String yieldingPoint,
    String tensileStrength,
    String elongationRatio,
    String camber,
    String annualCost
) {

    public HotRolledLineItem toHotRolledLineItemEntity(Inquiry inquiry) {

        return HotRolledLineItem.builder()
            .inquiry(inquiry)
            .kind(kind)
            .inqName(inqName)
            .orderCategory(orderCategory)
            .thickness(thickness)
            .width(width)
            .hardness(hardness)
            .flatness(flatness)
            .orderEdge(orderEdge)
            .quantity(quantity)
            .yieldingPoint(yieldingPoint)
            .tensileStrength(tensileStrength)
            .elongationRatio(elongationRatio)
            .camber(camber)
            .annualCost(annualCost)
            .build();
    }
}
