package com.pobluesky.lineitem.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.WireRodLineItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WireRodLineItemRepository extends JpaRepository<WireRodLineItem,Long> {

    @Query("SELECT c FROM WireRodLineItem c WHERE c.lineItemId = :lineItemId AND c.isActivated = true")
    Optional<WireRodLineItem> findActiveWireRodLineItemById(@Param("lineItemId") Long lineItemId);

    @Query("SELECT c FROM WireRodLineItem c WHERE c.inquiry = :inquiry AND c.isActivated = true")
    List<WireRodLineItem> findActiveWireRodLineItemByInquiry(Inquiry inquiry);
}
