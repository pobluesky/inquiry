package com.pobluesky.lineitem.dto.request.coldrolled;

import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.coldrolled.Kind;

public record ColdRolledLineItemUpdateRequestDTO(
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
    String yieldingPoint,
    String tensileStrength,
    String elongationRatio,
    String hardness
) {
}
