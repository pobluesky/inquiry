package com.pobluesky.inquiry.repository;

import static com.pobluesky.inquiry.entity.QInquiry.inquiry;

import com.pobluesky.feign.Customer;
import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.global.error.CommonException;
import com.pobluesky.global.error.ErrorCode;
import com.pobluesky.inquiry.dto.response.InquirySummaryResponseDTO;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import com.pobluesky.inquiry.entity.Progress;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
public class InquiryRepositoryImpl implements InquiryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private final UserClient userClient;

    @Override
    public List<InquirySummaryResponseDTO> findInquiriesByCustomerWithoutPaging(
        Long userId,
        Progress progress,
        ProductType productType,
        String customerName,
        InquiryType inquiryType,
        String salesPerson,
        Industry industry,
        LocalDate startDate,
        LocalDate endDate,
        String sortBy,
        String salesManagerName,
        String qualityManagerName
    ) {

        // Feign을 사용해 customer 정보를 가져옴
        Customer customer = userClient.getCustomerByIdWithoutToken(userId).getData();

        // 기본적으로 Inquiry 정보를 조회
        List<Inquiry> inquiries = queryFactory
            .selectFrom(inquiry)
            .where(
                inquiry.isActivated.eq(true),
                inquiry.userId.eq(userId),
                progressEq(progress),
                productTypeEq(productType),
                inquiryTypeEq(inquiryType),
                salesPersonContains(salesPerson),
                industryEq(industry),
                createdDateBetween(startDate, endDate)
            )
            .orderBy(getOrderSpecifier(sortBy))
            .fetch();

        // Feign을 사용해 각 Inquiry에 대해 Manager 정보를 조회 후 DTO로 변환
        return inquiries.stream().map(inq -> {
            // salesManager와 qualityManager 정보를 각각의 서비스에서 가져옴
            Manager salesManager = null;
            Manager qualityManager = null;

            // salesManagerId가 존재할 경우 Feign을 통해 salesManager 정보를 가져옴
            if (inq.getSalesManagerId() != null) {
                salesManager = userClient.getManagerByIdWithoutToken(inq.getSalesManagerId()).getData();
            }

            // qualityManagerId가 존재할 경우 Feign을 통해 qualityManager 정보를 가져옴
            if (inq.getQualityManagerId() != null) {
                qualityManager = userClient.getManagerByIdWithoutToken(inq.getQualityManagerId()).getData();
            }

            // DTO로 변환
            return InquirySummaryResponseDTO.builder()
                .inquiryId(inq.getInquiryId())
                .salesPerson(inq.getSalesPerson())
                .progress(inq.getProgress())
                .productType(inq.getProductType())
                .inquiryType(inq.getInquiryType())
                .customerName(customer.getCustomerName())
                .country(inq.getCountry())
                .corporate(inq.getCorporate())
                .corporationCode(inq.getCorporationCode())
                .industry(inq.getIndustry())
                .salesManagerName(salesManager != null ? salesManager.getName() : null)
                .qualityManagerName(qualityManager != null ? qualityManager.getName() : null)
                .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<InquirySummaryResponseDTO> findInquiriesBySalesManagerWithoutPaging(
        Progress progress,
        ProductType productType,
        String customerName,
        InquiryType inquiryType,
        String salesPerson,
        Industry industry,
        LocalDate startDate,
        LocalDate endDate,
        String sortBy,
        String salesManagerName,
        String qualityManagerName
    ) {
        // 기본적으로 Inquiry 정보를 조회
        List<Inquiry> inquiries = queryFactory
            .selectFrom(inquiry)
            .where(
                inquiry.isActivated.isTrue(),
                progressEq(progress),
                productTypeEq(productType),
                inquiryTypeEq(inquiryType),
                salesPersonContains(salesPerson),
                industryEq(industry),
                createdDateBetween(startDate, endDate)
            )
            .orderBy(getOrderSpecifier(sortBy))
            .fetch();

        // Feign을 사용해 각 Inquiry에 대해 Customer, Manager 정보를 조회 후 DTO로 변환
        return inquiries.stream()
            .map(inq -> {
                // Feign을 사용해 고객 정보를 조회
                Customer customer = userClient.getCustomerByIdWithoutToken(inq.getUserId()).getData();

                // salesManager와 qualityManager 정보를 각각의 서비스에서 가져옴
                Manager salesManager = inq.getSalesManagerId() != null
                    ? userClient.getManagerByIdWithoutToken(inq.getSalesManagerId()).getData()
                    : null;
                Manager qualityManager = inq.getQualityManagerId() != null
                    ? userClient.getManagerByIdWithoutToken(inq.getQualityManagerId()).getData()
                    : null;

                // DTO로 변환
                return InquirySummaryResponseDTO.builder()
                    .inquiryId(inq.getInquiryId())
                    .salesPerson(inq.getSalesPerson())
                    .progress(inq.getProgress())
                    .productType(inq.getProductType())
                    .inquiryType(inq.getInquiryType())
                    .customerName(customer != null ? customer.getCustomerName() : null) // Customer 정보
                    .country(inq.getCountry())
                    .corporate(inq.getCorporate())
                    .corporationCode(inq.getCorporationCode())
                    .industry(inq.getIndustry())
                    .salesManagerName(salesManager != null ? salesManager.getName() : null)
                    .qualityManagerName(qualityManager != null ? qualityManager.getName() : null)
                    .build();
            })
            // customerName이 존재하면 필터링
            .filter(dto -> StringUtils.isEmpty(customerName) || dto.customerName().contains(customerName))
            .collect(Collectors.toList());
    }

    @Override
    public List<InquirySummaryResponseDTO> findInquiriesByQualityManagerWithoutPaging(
        Progress progress,
        ProductType productType,
        String customerName,
        InquiryType inquiryType,
        String salesPerson,
        Industry industry,
        LocalDate startDate,
        LocalDate endDate,
        String sortBy,
        String salesManagerName,
        String qualityManagerName
    ) {
        // 기본적으로 Inquiry 정보를 조회
        List<Inquiry> inquiries = queryFactory
            .selectFrom(inquiry)
            .where(
                inquiry.isActivated.isTrue(),
                progressInQualityReviewStates(),
                progressEq(progress),
                productTypeEq(productType),
                inquiryTypeEq(inquiryType),
                salesPersonContains(salesPerson),
                industryEq(industry),
                createdDateBetween(startDate, endDate)
            )
            .orderBy(getOrderSpecifier(sortBy))
            .fetch();

        // Feign을 사용해 각 Inquiry에 대해 Customer, Manager 정보를 조회 후 DTO로 변환
        return inquiries.stream().map(inq -> {
                // Feign을 사용해 고객 정보를 조회
                Customer customer = userClient.getCustomerByIdWithoutToken(inq.getUserId()).getData();

                // salesManager와 qualityManager 정보를 각각의 서비스에서 가져옴
                Manager salesManager = null;
                Manager qualityManager = null;

                // salesManagerId가 존재할 경우 Feign을 통해 salesManager 정보를 가져옴
                if (inq.getSalesManagerId() != null) {
                    salesManager = userClient.getManagerByIdWithoutToken(inq.getSalesManagerId()).getData();
                }

                // qualityManagerId가 존재할 경우 Feign을 통해 qualityManager 정보를 가져옴
                if (inq.getQualityManagerId() != null) {
                    qualityManager = userClient.getManagerByIdWithoutToken(inq.getQualityManagerId()).getData();
                }

                // DTO로 변환
                InquirySummaryResponseDTO dto = InquirySummaryResponseDTO.builder()
                    .inquiryId(inq.getInquiryId())
                    .salesPerson(inq.getSalesPerson())
                    .progress(inq.getProgress())
                    .productType(inq.getProductType())
                    .inquiryType(inq.getInquiryType())
                    .customerName(customer != null ? customer.getCustomerName() : null) // Customer 정보
                    .country(inq.getCountry())
                    .corporate(inq.getCorporate())
                    .corporationCode(inq.getCorporationCode())
                    .industry(inq.getIndustry())
                    .salesManagerName(salesManager != null ? salesManager.getName() : null) // Sales Manager 정보
                    .qualityManagerName(qualityManager != null ? qualityManager.getName() : null) // Quality Manager 정보
                    .build();

                return dto;
            })
            // customerName 필터링을 메모리에서 수행
            .filter(dto -> StringUtils.isEmpty(customerName) || dto.customerName().contains(customerName))
            .collect(Collectors.toList());
    }

    private OrderSpecifier<?>[] getOrderSpecifier(String sortBy) {
        switch (sortBy) {
            case "LATEST":
                return new OrderSpecifier[]{
                    inquiry.createdDate.desc().nullsLast(),
                    inquiry.inquiryId.desc()
                };
            case "OLDEST":
                return new OrderSpecifier[]{
                    inquiry.createdDate.asc().nullsFirst(),
                    inquiry.inquiryId.asc()
                };
            default:
                throw new CommonException(ErrorCode.INVALID_ORDER_CONDITION);
        }
    }

    private BooleanExpression progressEq(Progress progress) {
        return progress != null ? inquiry.progress.eq(progress) : null;
    }

    private BooleanExpression productTypeEq(ProductType productType) {
        return productType != null ? inquiry.productType.eq(productType) : null;
    }

//    private BooleanExpression customerNameContains(String customerName) {
//        return StringUtils.hasText(customerName) ? customer.customerName.contains(customerName) : null;
//    }

    private BooleanExpression inquiryTypeEq(InquiryType inquiryType) {
        return inquiryType != null ? inquiry.inquiryType.eq(inquiryType) : null;
    }

    private BooleanExpression salesPersonContains(String salesPerson) {
        return StringUtils.hasText(salesPerson) ? inquiry.salesPerson.contains(salesPerson) : null;
    }

//    private BooleanExpression salesManagerNameEq(String name) {
//        return name != null ? salesManager.name.eq(name) : null;
//    }
//
//    private BooleanExpression qualityManagerNameEq(String name) {
//        return name != null ? qualityManager.name.eq(name) : null;
//    }

    private BooleanExpression industryEq(Industry industry) {
        return industry != null ? inquiry.industry.eq(industry) : null;
    }

    private BooleanExpression createdDateBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null && endDate == null) {
            return null;
        }

        DateTemplate<LocalDate> dateTemplate = Expressions.dateTemplate(
            LocalDate.class,
            "CAST({0} AS DATE)",
            inquiry.createdDate
        );

        if (startDate != null && endDate != null) {
            return dateTemplate.between(startDate, endDate);
        } else if (startDate != null) {
            return dateTemplate.goe(startDate);
        } else {
            return dateTemplate.loe(endDate);
        }
    }

    private BooleanExpression progressInQualityReviewStates() {
        return inquiry.progress.in(
            Progress.QUALITY_REVIEW_REQUEST,
            Progress.QUALITY_REVIEW_RESPONSE,
            Progress.QUALITY_REVIEW_COMPLETED,
            Progress.FINAL_REVIEW_COMPLETED
        );
    }
}
