package com.pobluesky.review.entity;

import jakarta.persistence.Embeddable;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SalesInfo {

    @Enumerated(EnumType.STRING)
    private ContractType contract;

    private String thicknessNotify;
}
