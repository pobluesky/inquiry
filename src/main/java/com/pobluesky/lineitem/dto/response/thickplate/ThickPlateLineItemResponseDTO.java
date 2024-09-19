package com.pobluesky.lineitem.dto.response.thickplate;

import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;
import com.pobluesky.lineitem.entity.ThickPlateLineItem;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ThickPlateLineItemResponseDTO extends LineItemResponseDTO {

    private Long lineItemId;

    private Long inquiryId;

    private String orderPurpose;

    private String orderInfo;

    private String ladleIngredient;

    private String productIngredient;

    private String seal;

    private Boolean grainSizeAnalysis;

    private String show;

    private String extraShow;

    private String agingShow;

    private String curve;

    private String additionalRequests;

    private Boolean isActivated;

    private String hardness;

    private Boolean dropWeightTest;

    private Boolean ultrasonicTransducer;

    public  static ThickPlateLineItemResponseDTO from(ThickPlateLineItem thickPlateLineItem){

        return ThickPlateLineItemResponseDTO.builder()
            .lineItemId(thickPlateLineItem.getLineItemId())
            .inquiryId(thickPlateLineItem.getInquiry().getInquiryId())
            .orderPurpose(thickPlateLineItem.getOrderPurpose())
            .orderInfo(thickPlateLineItem.getOrderInfo())
            .ladleIngredient(thickPlateLineItem.getLadleIngredient())
            .productIngredient(thickPlateLineItem.getProductIngredient())
            .seal(thickPlateLineItem.getSeal())
            .grainSizeAnalysis(thickPlateLineItem.getGrainSizeAnalysis())
            .show(thickPlateLineItem.getShow())
            .extraShow(thickPlateLineItem.getExtraShow())
            .agingShow(thickPlateLineItem.getAgingShow())
            .curve(thickPlateLineItem.getCurve())
            .additionalRequests(thickPlateLineItem.getAdditionalRequests())
            .isActivated(thickPlateLineItem.getIsActivated())
            .hardness(thickPlateLineItem.getHardness())
            .dropWeightTest(thickPlateLineItem.getDropWeightTest())
            .ultrasonicTransducer(thickPlateLineItem.getUltrasonicTransducer())
            .build();
    }
}
