package com.pobluesky.lineitem.dto.request.thickplate;

public record ThickPlateLineItemUpdateRequestDTO(
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

}
