package com.pobluesky.receipt.dto.response;

import com.pobluesky.receipt.entity.Receipt;
import lombok.Builder;

@Builder
public record ReceiptResponse (
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
    public static ReceiptResponse from(Receipt receipt) {

        return ReceiptResponse.builder()
            .product(receipt.getProduct())
            .specification(receipt.getSpecification())
            .surfaceFinish(receipt.getSurfaceFinish())
            .usage(receipt.getUsage())
            .thickness(receipt.getThickness())
            .diameter(receipt.getDiameter())
            .width(receipt.getWidth())
            .quantity(receipt.getQuantity())
            .price(receipt.getPrice())
            .unitMinWeight(receipt.getUnitMinWeight())
            .unitMaxWeight(receipt.getUnitMaxWeight())
            .edge(receipt.getEdge())
            .build();
    }
}
