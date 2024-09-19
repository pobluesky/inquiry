package com.pobluesky.offersheet.dto.response;

import com.pobluesky.feign.Customer;
import com.pobluesky.feign.UserClient;
import com.pobluesky.offersheet.entity.OfferSheet;
import com.pobluesky.receipt.dto.response.ReceiptResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;

@Builder
public record OfferSheetResponseDTO(
    Long offerSheetId,
    Long inquiryId,
    String customerName,
    String priceTerms,
    String paymentTerms,
    LocalDate shipment,
    LocalDate validity,
    String destination,
    String remark,
    String message,
    List<ReceiptResponse> receipts
) {
    public static OfferSheetResponseDTO from(OfferSheet offerSheet, UserClient userClient) {
        Customer customer = userClient.getCustomerByIdWithoutToken(offerSheet.getInquiry().getUserId()).getData();

        return OfferSheetResponseDTO.builder()
            .offerSheetId(offerSheet.getOfferSheetId())
            .inquiryId(offerSheet.getInquiry().getInquiryId())
            .customerName(customer.getCustomerName())
            .priceTerms(offerSheet.getPriceTerms())
            .paymentTerms(offerSheet.getPaymentTerms())
            .shipment(offerSheet.getShipment())
            .validity(offerSheet.getValidity())
            .destination(offerSheet.getDestination())
            .remark(offerSheet.getRemark())
            .message(offerSheet.getMessage())
            .receipts(offerSheet.getReceipts()
                .stream()
                .map(ReceiptResponse::from)
                .collect(Collectors.toList()))
            .build();
    }
}
