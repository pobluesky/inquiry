package com.pobluesky.lineitem.entity;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.type.InqName;
import com.pobluesky.lineitem.entity.type.wirerod.Kind;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wirerod_line_items")
public class WireRodLineItem extends LineItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Enumerated(EnumType.STRING)
    private InqName inqName;

    private String orderCategory;

    private String diameter;

    private Integer quantity;

    private String expectedDeadline;

    private Integer initialQuantity;

    private String customerProcessing;

    private String finalUse;

    private String transportationDestination;

    private String annualCost;

    private String legalRegulatoryReview;

    private String legalRegulatoryReviewDetail;

    private String finalCustomer;

    @Builder
    public WireRodLineItem(
        Inquiry inquiry,
        Kind kind,
        InqName inqName,
        String orderCategory,
        String diameter,
        Integer quantity,
        String expectedDeadline,
        Integer initialQuantity,
        String customerProcessing,
        String finalUse,
        String transportationDestination,
        String annualCost,
        String legalRegulatoryReview,
        String legalRegulatoryReviewDetail,
        String finalCustomer
    ){
        this.inquiry = inquiry;
        this.kind = kind;
        this.inqName = inqName;
        this.orderCategory = orderCategory;
        this.diameter = diameter;
        this.quantity = quantity;
        this.expectedDeadline = expectedDeadline;
        this.initialQuantity = initialQuantity;
        this.customerProcessing = customerProcessing;
        this.finalUse = finalUse;
        this.isActivated = true;
        this.transportationDestination = transportationDestination;
        this.annualCost = annualCost;
        this.legalRegulatoryReview = legalRegulatoryReview;
        this.legalRegulatoryReviewDetail = legalRegulatoryReviewDetail;
        this.finalCustomer = finalCustomer;
    }
}
