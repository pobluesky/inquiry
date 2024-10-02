package com.pobluesky.inquiry.dto.request;

import com.pobluesky.inquiry.entity.Country;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import java.util.List;
import java.util.Map;

public record InquiryUpdateRequestDTO(
    Country country,
    String corporate,
    String salesPerson,
    InquiryType inquiryType,
    Industry industry,
    ProductType productType,
    String customerRequestDate,
    String additionalRequests,
    String responseDeadline,
    List<Map<String, Object>> lineItemRequestDTOs,
    Boolean isFileDeleted
) {
}
