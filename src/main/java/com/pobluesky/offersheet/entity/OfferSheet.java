package com.pobluesky.offersheet.entity;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.receipt.entity.Receipt;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offersheet")
public class OfferSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerSheetId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @OneToMany(mappedBy = "offerSheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receipt> receipts = new ArrayList<>();

    private String priceTerms;

    private String paymentTerms;

    private LocalDate shipment;

    private LocalDate validity;

    private String destination;

    private String remark;

    private String message;

    @Builder
    public OfferSheet(
        Inquiry inquiry,
        String priceTerms,
        String paymentTerms,
        LocalDate shipment,
        LocalDate validity,
        String destination,
        String remark,
        String message
    ) {
        this.inquiry = inquiry;
        this.priceTerms = priceTerms;
        this.paymentTerms = paymentTerms;
        this.shipment = shipment;
        this.validity = validity;
        this.destination = destination;
        this.remark = remark;
        this.message = message;
    }

    public void updateOfferSheet(
        String priceTerms,
        String paymentTerms,
        LocalDate shipment,
        LocalDate validity,
        String destination,
        String remark
    ) {
        this.priceTerms = priceTerms;
        this.paymentTerms = paymentTerms;
        this.shipment = shipment;
        this.validity = validity;
        this.destination = destination;
        this.remark = remark;
    }
}
