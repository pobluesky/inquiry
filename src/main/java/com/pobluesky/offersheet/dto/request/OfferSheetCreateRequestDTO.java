package com.pobluesky.offersheet.dto.request;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.offersheet.entity.OfferSheet;
import com.pobluesky.receipt.dto.request.ReceiptCreateRequestDTO;
import java.time.LocalDate;
import java.util.List;

public record OfferSheetCreateRequestDTO(
    String priceTerms,
    String paymentTerms,
    LocalDate shipment,
    LocalDate validity,
    String destination,
    String remark,
    String message,
    List<ReceiptCreateRequestDTO> receipts
) {
    public OfferSheet toOfferSheetEntity(Inquiry inquiry) {

        return OfferSheet.builder()
            .inquiry(inquiry)
            .priceTerms(priceTerms)
            .paymentTerms(paymentTerms)
            .shipment(shipment)
            .validity(validity)
            .destination(destination)
            .remark(remark)
            .message(message)
            .build();
    }
}
