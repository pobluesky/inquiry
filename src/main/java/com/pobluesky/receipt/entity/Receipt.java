package com.pobluesky.receipt.entity;

import com.pobluesky.global.BaseEntity;
import com.pobluesky.offersheet.entity.OfferSheet;
import jakarta.persistence.Entity;
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

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "receipts")
public class Receipt extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_sheet_id")
    private OfferSheet offerSheet;

    private String product;

    private String specification;

    private String surfaceFinish;

    private String usage;

    private String thickness;

    private String diameter;

    private String width;

    private String quantity;

    private String price;

    private String unitMinWeight;

    private String unitMaxWeight;

    private String edge;

    protected Boolean isActivated;

    @Builder
    public Receipt(
        OfferSheet offerSheet,
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
        this.offerSheet = offerSheet;
        this.product = product;
        this.specification = specification;
        this.surfaceFinish = surfaceFinish;
        this.usage = usage;
        this.thickness = thickness;
        this.diameter = diameter;
        this.width = width;
        this.quantity = quantity;
        this.price = price;
        this.unitMinWeight = unitMinWeight;
        this.unitMaxWeight = unitMaxWeight;
        this.edge = edge;
        this.isActivated = true;
    }

    public void updateReceipt(
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
        this.product = product;
        this.specification = specification;
        this.surfaceFinish = surfaceFinish;
        this.usage = usage;
        this.thickness = thickness;
        this.diameter = diameter;
        this.width = width;
        this.quantity = quantity;
        this.price = price;
        this.unitMinWeight = unitMinWeight;
        this.unitMaxWeight = unitMaxWeight;
        this.edge = edge;
    }

    public void deleteReceipt(){
        this.isActivated = false;
    }
}
