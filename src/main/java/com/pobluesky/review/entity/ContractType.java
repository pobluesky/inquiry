package com.pobluesky.review.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContractType {

    CUSTOMER_RELATIONSHIP("customer_relationship"),
    MARKET_DEMAND("market_demand");

    private final String key;
}
