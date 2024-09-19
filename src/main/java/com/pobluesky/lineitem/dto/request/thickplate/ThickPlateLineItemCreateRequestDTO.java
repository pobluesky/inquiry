package com.pobluesky.lineitem.dto.request.thickplate;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.ThickPlateLineItem;

public record ThickPlateLineItemCreateRequestDTO(
    String orderPurpose,
    String orderInfo,
    String ladleIngredient,
    String productIngredient,
    String seal,
    Boolean grainSizeAnalysis,
    String show,
    String extraShow,
    String agingShow,
    String curve,
    String additionalRequests,
    String hardness,
    Boolean dropWeightTest,
    Boolean ultrasonicTransducer
) {

    public ThickPlateLineItem toThickPlateLineItemEntity(Inquiry inquiry) {

        return ThickPlateLineItem.builder()
            .inquiry(inquiry)
            .orderPurpose(orderPurpose)
            .orderInfo(orderInfo)
            .ladleIngredient(ladleIngredient)
            .productIngredient(productIngredient)
            .seal(seal)
            .grainSizeAnalysis(grainSizeAnalysis)
            .show(show)
            .extraShow(extraShow)
            .agingShow(agingShow)
            .curve(curve)
            .additionalRequests(additionalRequests)
            .hardness(hardness)
            .dropWeightTest(dropWeightTest)
            .ultrasonicTransducer(ultrasonicTransducer)
            .build();

    }
}
