package com.pobluesky.lineitem.entity;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.type.car.IxPlate;
import com.pobluesky.lineitem.entity.type.car.Lab;
import com.pobluesky.lineitem.entity.type.car.StandardOrg;
import com.pobluesky.lineitem.entity.type.car.Kind;

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
@Table(name = "car_line_items")
public class CarLineItem extends LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @Enumerated(EnumType.STRING)
    private Lab lab;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    @Enumerated(EnumType.STRING)
    private StandardOrg standardOrg;

    private String salesVehicleName;

    private String partName;

    @Enumerated(EnumType.STRING)
    private IxPlate ixPlate;

    private String thickness;

    private String width;

    private Integer quantity;

    private String expectedDeliveryDate;

    private String transportationDestination;

    private String orderEdge;

    private String tolerance;

    private String annualCost;

    @Builder
    public CarLineItem(
        Inquiry inquiry,
        Lab lab,
        Kind kind,
        StandardOrg standardOrg,
        String salesVehicleName,
        String partName,
        IxPlate ixPlate,
        String thickness,
        String width,
        Integer quantity,
        String expectedDeliveryDate,
        String transportationDestination,
        String orderEdge,
        String tolerance,
        String annualCost
    ) {
        this.inquiry=inquiry;
        this.lab = lab;
        this.kind = kind;
        this.standardOrg = standardOrg;
        this.salesVehicleName = salesVehicleName;
        this.partName = partName;
        this.ixPlate = ixPlate;
        this.thickness = thickness;
        this.width = width;
        this.quantity = quantity;
        this.isActivated = true;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.transportationDestination = transportationDestination;
        this.orderEdge = orderEdge;
        this.tolerance = tolerance;
        this.annualCost = annualCost;
    }
}
