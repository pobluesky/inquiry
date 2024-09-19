package com.pobluesky.quality.dto.request;

import com.pobluesky.quality.entity.QualityReviewInfo;

public record QualityUpdateRequestDTO(
    QualityReviewInfo qualityReviewInfo,
    String qualityComments
) {
}
