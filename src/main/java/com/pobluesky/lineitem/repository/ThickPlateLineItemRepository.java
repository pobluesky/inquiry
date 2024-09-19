package com.pobluesky.lineitem.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.ThickPlateLineItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThickPlateLineItemRepository extends JpaRepository<ThickPlateLineItem, Long> {


    @Query("SELECT c FROM ThickPlateLineItem c WHERE c.lineItemId = :lineItemId AND c.isActivated = true")
    Optional<ThickPlateLineItem> findActiveThickPlateLineItemById(@Param("lineItemId") Long lineItemId);

    @Query("SELECT c FROM ThickPlateLineItem c WHERE c.inquiry = :inquiry AND c.isActivated = true")
    List<ThickPlateLineItem> findActiveThickPlateLineItemByInquiry(Inquiry inquiry);
}
