package com.pobluesky.inquiry.dto.response;


import com.pobluesky.feign.Manager;
import lombok.Builder;

@Builder
public record ManagerSummaryResponseDTO(
    Long userId,
    String name
) {

    public static ManagerSummaryResponseDTO from(Manager manager) {
        if (manager == null) return null;
        else {
            return ManagerSummaryResponseDTO.builder()
                .userId(manager.getUserId())
                .name(manager.getName())
                .build();
        }
    }
}
