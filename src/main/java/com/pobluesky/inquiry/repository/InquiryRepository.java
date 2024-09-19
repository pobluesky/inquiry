package com.pobluesky.inquiry.repository;

import com.pobluesky.feign.Manager;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.ProductType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryRepositoryCustom{

    @Query("SELECT i FROM Inquiry i WHERE i.inquiryId = :inquiryId AND i.userId = :userId AND i.isActivated = true")
    Optional<Inquiry> findByCustomerIdAndInquiryId(Long userId, Long inquiryId);

    @Query("SELECT i FROM Inquiry i WHERE i.inquiryId = :inquiryId AND i.isActivated = true")
    Optional<Inquiry> findActiveInquiryByInquiryId(Long inquiryId);

    @Query("SELECT i FROM Inquiry i WHERE i.isActivated = true")
    List<Inquiry> findActiveInquiries();

    @Query("SELECT i FROM Inquiry i "
        + "WHERE i.userId = :customerId "
        + "AND i.productType = :productType "
        + "AND i.isActivated = true "
        + "ORDER BY i.createdDate DESC")
    List<Inquiry> findInquiriesByCustomerIdAndProductType(Long customerId, ProductType productType);

    @Query("SELECT i FROM Inquiry i "
        + "WHERE i.userId = :customerId "
        + "AND i.productType = :productType "
        + "AND i.isActivated = true "
        + "AND i.isFavorite = true "
        + "ORDER BY i.createdDate DESC")
    List<Inquiry> findFavoriteInquiriesByCustomerIdAndProductType(Long customerId, ProductType productType);

    @Query(value = "SELECT EXTRACT(MONTH FROM i.created_date) AS month, " +
        "ROUND(AVG(EXTRACT(DAY FROM (i.modified_date - i.created_date))), 1) AS avgDays " +
        "FROM Inquiry i " +
        "GROUP BY EXTRACT(MONTH FROM i.created_date) " +
        "ORDER BY EXTRACT(MONTH FROM i.created_date)", nativeQuery = true)
    List<Object[]> findAverageDaysPerMonth();

    @Query(value = "WITH months AS (" +
        "    SELECT generate_series(1, 12) AS month" +
        ")" +
        "SELECT m.month, " +
        "       COALESCE(ROUND(AVG(EXTRACT(DAY FROM (i.modified_date - i.created_date))), 1), 0) AS avgDays " +
        "FROM months m " +
        "LEFT JOIN Inquiry i " +
        "ON EXTRACT(MONTH FROM i.created_date) = m.month " +
        "AND i.sales_manager_id = :managerId " +
        "GROUP BY m.month " +
        "ORDER BY m.month",
        nativeQuery = true)
    List<Object[]> findAverageDaysPerMonthBySalesManager(@Param("managerId") Long managerId);

    @Query(value = "WITH months AS (" +
        "    SELECT generate_series(1, 12) AS month" +
        ")" +
        "SELECT m.month, " +
        "       COALESCE(ROUND(AVG(EXTRACT(DAY FROM (i.modified_date - i.created_date))), 1), 0) AS avgDays " +
        "FROM months m " +
        "LEFT JOIN Inquiry i " +
        "ON EXTRACT(MONTH FROM i.created_date) = m.month " +
        "AND i.quality_manager_id = :managerId " +
        "GROUP BY m.month " +
        "ORDER BY m.month",
        nativeQuery = true)
    List<Object[]> findAverageDaysPerMonthByQualityManager(@Param("managerId") Long managerId);

    @Query("SELECT i.progress AS progress, COUNT(i) AS count " +
        "FROM Inquiry i " +
        "GROUP BY i.progress")
    List<Object[]> countInquiriesByProgress();

    @Query("SELECT i.progress AS progress, COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.salesManagerId = :userId " +
        "GROUP BY i.salesManagerId, i.progress")
    List<Object[]> countInquiriesBySalesManagerAndProgress(@Param("userId") Long userId);

    @Query("SELECT i.progress AS progress, COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.qualityManagerId =:userId " +
        "GROUP BY i.salesManagerId, i.progress")
    List<Object[]> countInquiriesByQualityManagerAndProgress(@Param("userId") Long userId);

    @Query("SELECT COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.progress = 'FINAL_REVIEW_COMPLETED'")
    Integer countInquiriesByFinalProgress();

    @Query("SELECT COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.salesManagerId = :userId AND " +
        "i.progress = 'FINAL_REVIEW_COMPLETED'")
    Integer countInquiriesByFinalProgressBySalesManager(@Param("userId") Long userId);

    @Query("SELECT COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.qualityManagerId = :userId AND " +
        "i.progress = 'FINAL_REVIEW_COMPLETED'")
    Integer countInquiriesByFinalProgressByQualityManager(@Param("userId") Long userId);

    @Query("SELECT COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.salesManagerId = :userId")
    Integer countInquiriesBySalesManager(@Param("userId") Long userId);

    @Query("SELECT COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.qualityManagerId =:userId")
    Integer countInquiriesByQualityManager(@Param("userId") Long userId);

    @Query("SELECT i.productType AS productType, COUNT(i) AS count " +
        "FROM Inquiry i " +
        "GROUP BY i.productType")
    List<Object[]> countInquiriesByProductType();

    @Query("SELECT i.productType AS productType, COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.salesManagerId =:userId " +
        "GROUP BY i.productType")
    List<Object[]> countInquiriesByProductTypeAndSalesManager(@Param("userId") Long userId);

    @Query("SELECT i.productType AS productType, COUNT(i) AS count " +
        "FROM Inquiry i " +
        "WHERE i.qualityManagerId = :userId " +
        "GROUP BY i.productType")
    List<Object[]> countInquiriesByProductTypeAndQualityManager(@Param("userId") Long userId);
}
