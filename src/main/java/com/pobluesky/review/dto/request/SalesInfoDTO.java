package com.pobluesky.review.dto.request;

import com.pobluesky.review.entity.ContractType;
import com.pobluesky.review.entity.SalesInfo;

public record SalesInfoDTO(
    ContractType contract,
    String thicknessNotify
) {
    public SalesInfo toSalesInfoEntity() {
        return new SalesInfo(contract, thicknessNotify);
    }
}
