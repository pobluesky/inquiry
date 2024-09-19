package com.pobluesky.receipt.dto.request;

import com.pobluesky.offersheet.entity.OfferSheet;
import com.pobluesky.receipt.entity.Receipt;

public record ReceiptCreateRequestDTO(
    String product,
    String specification,
    String surfaceFinish,
    String usage,
    String thickness,
    String diameter,
    String width,
    String quantity,
    String price,
    String unitMinWeight,
    String unitMaxWeight,
    String edge
) {
    public Receipt toReceiptEntity(OfferSheet offerSheet) {

        return Receipt.builder()
            .offerSheet(offerSheet)
            .product(product)
            .specification(specification)
            .surfaceFinish(surfaceFinish)
            .usage(usage)
            .thickness(thickness)
            .diameter(diameter)
            .width(width)
            .quantity(quantity)
            .price(price)
            .unitMinWeight(unitMinWeight)
            .unitMaxWeight(unitMaxWeight)
            .edge(edge)
            .build();
    }
}
