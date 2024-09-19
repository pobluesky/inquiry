package com.pobluesky.lineitem.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.HotRolledLineItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HotRolledLineItemRepository extends JpaRepository<HotRolledLineItem, Long> {

    @Query("SELECT c FROM HotRolledLineItem c WHERE c.inquiry = :inquiry AND c.isActivated = true")
    List<HotRolledLineItem> findActiveHotRolledLineItemByInquiry(Inquiry inquiry);

    @Query("SELECT c FROM HotRolledLineItem c WHERE c.lineItemId = :lineItemId AND c.isActivated = true")
    Optional<HotRolledLineItem> findActiveHotRolledLineItemById(@Param("lineItemId") Long lineItemId);
}
