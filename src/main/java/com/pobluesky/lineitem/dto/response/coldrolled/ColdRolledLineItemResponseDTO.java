package com.pobluesky.lineitem.dto.response.coldrolled;

import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;
import com.pobluesky.lineitem.entity.ColdRolledLineItem;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.coldrolled.Kind;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ColdRolledLineItemResponseDTO extends LineItemResponseDTO {

    private Long lineItemId;

    private Long inquiryId;

    private Kind kind;

    private InqName inqName;

    private String orderCategory;

    private String thickness;

    private String width;

    private Integer quantity;

    private String expectedDeadline;

    private String orderEdge;

    private String inDiameter;

    private String outDiameter;

    private Boolean isActivated;

    private String sleeveThickness;

    private String tensileStrength;

    private String elongationRatio;

    private String hardness;

    public static ColdRolledLineItemResponseDTO from(ColdRolledLineItem coldRolledLineItem) {

        return ColdRolledLineItemResponseDTO.builder()
            .lineItemId(coldRolledLineItem.getLineItemId())
            .inquiryId(coldRolledLineItem.getInquiry().getInquiryId())
            .kind(coldRolledLineItem.getKind())
            .inqName(coldRolledLineItem.getInqName())
            .orderCategory(coldRolledLineItem.getOrderCategory())
            .thickness(coldRolledLineItem.getThickness())
            .width(coldRolledLineItem.getWidth())
            .quantity(coldRolledLineItem.getQuantity())
            .orderEdge(coldRolledLineItem.getOrderEdge())
            .inDiameter(coldRolledLineItem.getInDiameter())
            .outDiameter(coldRolledLineItem.getOutDiameter())
            .isActivated(coldRolledLineItem.getIsActivated())
            .sleeveThickness(coldRolledLineItem.getSleeveThickness())
            .tensileStrength(coldRolledLineItem.getTensileStrength())
            .elongationRatio(coldRolledLineItem.getElongationRatio())
            .hardness(coldRolledLineItem.getHardness())
            .expectedDeadline(coldRolledLineItem.getExpectedDeadline())
            .build();
    }
}
