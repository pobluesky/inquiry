package com.pobluesky.lineitem.dto.request.coldrolled;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.ColdRolledLineItem;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.coldrolled.Kind;

public record ColdRolledLineItemCreateRequestDTO(
    Kind kind,
    InqName inqName,
    String orderCategory,
    String thickness,
    String width,
    Integer quantity,
    String orderEdge,
    String inDiameter,
    String outDiameter,
    String sleeveThickness,
    String expectedDeadline,
    String tensileStrength,
    String elongationRatio,
    String hardness
) {
    public ColdRolledLineItem toColdRolledLineItemEntity(Inquiry inquiry)
    {

        return ColdRolledLineItem.builder()
            .inquiry(inquiry)
            .kind(kind)
            .inqName(inqName)
            .orderCategory(orderCategory)
            .thickness(thickness)
            .width(width)
            .quantity(quantity)
            .orderEdge(orderEdge)
            .inDiameter(inDiameter)
            .outDiameter(outDiameter)
            .expectedDeadline(expectedDeadline)
            .sleeveThickness(sleeveThickness)
            .tensileStrength(tensileStrength)
            .elongationRatio(elongationRatio)
            .hardness(hardness)
            .build();
    }
}
