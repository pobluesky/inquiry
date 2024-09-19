package com.pobluesky.lineitem.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.ColdRolledLineItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColdRolledLineItemRepository extends JpaRepository<ColdRolledLineItem, Long> {

    @Query("SELECT c FROM ColdRolledLineItem c WHERE c.lineItemId = :lineItemId AND c.isActivated = true")
    Optional<ColdRolledLineItem> findActiveColdRolledLineItemById(@Param("lineItemId") Long lineItemId);

    @Query("SELECT c FROM ColdRolledLineItem c WHERE c.inquiry = :inquiry AND c.isActivated = true")
    List<ColdRolledLineItem> findActiveColdRolledLineItemByInquiry(Inquiry inquiry);
}
