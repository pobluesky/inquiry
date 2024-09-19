package com.pobluesky.lineitem.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.lineitem.entity.CarLineItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarLineItemRepository extends JpaRepository<CarLineItem, Long> {

    @Query("SELECT c FROM CarLineItem c WHERE c.inquiry = :inquiry AND c.isActivated = true")
    List<CarLineItem> findAllActiveCarLineItemByInquiry(@Param("inquiry") Inquiry inquiry);

    @Query("SELECT c FROM CarLineItem c WHERE c.lineItemId = :lineItemId AND c.isActivated = true")
    Optional<CarLineItem> findActiveCarLineItemById(@Param("lineItemId") Long lineItemId);

    @Query("SELECT c FROM CarLineItem c WHERE c.inquiry = :inquiry AND c.isActivated = true")
    List<CarLineItem> findActiveCarLineItemByInquiry(@Param("inquiry") Inquiry inquiry);

    @Query("SELECT c FROM CarLineItem c WHERE c.lineItemId = :lineItemId AND c.inquiry = :inquiry AND c.isActivated = true")
    Optional<CarLineItem> findActiveCarLineItemByIdAndInquiry(@Param("lineItemId") Long lineItemId, @Param("inquiry") Inquiry inquiry);

}
