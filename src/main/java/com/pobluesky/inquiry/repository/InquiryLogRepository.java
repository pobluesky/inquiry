package com.pobluesky.inquiry.repository;

import com.pobluesky.inquiry.entity.InquiryLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryLogRepository extends JpaRepository<InquiryLog, Long> {
    List<InquiryLog> findByInquiryInquiryIdOrderByCreatedDateAsc(Long inquiryId);
}
