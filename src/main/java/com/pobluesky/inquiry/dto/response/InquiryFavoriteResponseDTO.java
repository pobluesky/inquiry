package com.pobluesky.inquiry.dto.response;

import com.pobluesky.feign.Customer;
import com.pobluesky.feign.UserClient;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.ProductType;
import com.pobluesky.lineitem.dto.response.LineItemResponseDTO;
import java.util.List;
import lombok.Builder;

@Builder
public record InquiryFavoriteResponseDTO(
    Long inquiryId,
    String salesPerson,
    Industry industry,
    String customerName,
    ProductType productType,
    List<LineItemResponseDTO> lineItemList,
    Boolean isFavorite
) {
    public static InquiryFavoriteResponseDTO of(Inquiry inquiry, List<LineItemResponseDTO> lineItems
    , UserClient userClient) {
        Customer customer = userClient.getCustomerByIdWithoutToken(inquiry.getUserId()).getData();

        return InquiryFavoriteResponseDTO.builder()
            .inquiryId(inquiry.getInquiryId())
            .salesPerson(inquiry.getSalesPerson())
            .industry(inquiry.getIndustry())
            .customerName(customer.getCustomerName())
            .productType(inquiry.getProductType())
            .lineItemList(lineItems)
            .isFavorite(inquiry.getIsFavorite())
            .build();
    }
}
