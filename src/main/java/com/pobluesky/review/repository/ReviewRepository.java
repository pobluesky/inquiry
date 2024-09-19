package com.pobluesky.review.repository;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.review.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByInquiry(Inquiry inquiry);

    boolean existsByInquiry(Inquiry inquiry);
}
