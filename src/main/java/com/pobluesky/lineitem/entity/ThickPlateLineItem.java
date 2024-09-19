package com.pobluesky.lineitem.entity;

import com.pobluesky.inquiry.entity.Inquiry;
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

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "thickplate_line_items")
public class ThickPlateLineItem extends LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    private String orderPurpose;

    private String orderInfo;

    private String ladleIngredient;

    private String productIngredient;

    private String seal;

    private Boolean grainSizeAnalysis;

    private String show;

    private String extraShow;

    private String agingShow;

    private String curve;

    private String additionalRequests;

    private String hardness;

    private Boolean dropWeightTest;

    private Boolean ultrasonicTransducer;

    @Builder
    public ThickPlateLineItem(
        Inquiry inquiry,
        String orderPurpose,
        String orderInfo,
        String ladleIngredient,
        String productIngredient,
        String seal,
        Boolean grainSizeAnalysis,
        String show,
        String extraShow,
        String agingShow,
        String curve,
        String additionalRequests,
        String hardness,
        Boolean dropWeightTest,
        Boolean ultrasonicTransducer
    ){
        this.inquiry = inquiry;
        this.orderPurpose = orderPurpose;
        this.orderInfo = orderInfo;
        this.ladleIngredient = ladleIngredient;
        this.productIngredient = productIngredient;
        this.seal = seal;
        this.grainSizeAnalysis = grainSizeAnalysis;
        this.show = show;
        this.extraShow = extraShow;
        this.agingShow = agingShow;
        this.curve = curve;
        this.additionalRequests = additionalRequests;
        this.isActivated = true;
        this.hardness = hardness;
        this.dropWeightTest = dropWeightTest;
        this.ultrasonicTransducer = ultrasonicTransducer;
    }
}
