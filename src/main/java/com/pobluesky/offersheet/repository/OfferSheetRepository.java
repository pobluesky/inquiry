package com.pobluesky.offersheet.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.offersheet.entity.OfferSheet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferSheetRepository extends JpaRepository<OfferSheet, Long> {
    Optional<OfferSheet> findByInquiry(Inquiry inquiry);

    Optional<OfferSheet> findByInquiryInquiryId(Long inquiryId);
}
