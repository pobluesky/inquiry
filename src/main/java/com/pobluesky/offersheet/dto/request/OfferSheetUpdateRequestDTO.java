package com.pobluesky.offersheet.dto.request;

import java.time.LocalDate;

public record OfferSheetUpdateRequestDTO(
    String priceTerms,
    String paymentTerms,
    LocalDate shipment,
    LocalDate validity,
    String destination,
    String remark
) {}
