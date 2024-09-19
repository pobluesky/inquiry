package com.pobluesky.quality.dto.request;

import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.quality.entity.Quality;
import com.pobluesky.quality.entity.QualityReviewInfo;

public record QualityCreateRequestDTO(
    QualityReviewInfoCreateRequestDTO qualityReviewInfo,
    String qualityComments
) {
    // dto -> entity
    public Quality toQualityEntity(Inquiry inquiry, String fileName, String filePath) {
        QualityReviewInfo qualityReviewInfoEntity = qualityReviewInfo.toQualityReviewInfoEntity(fileName, filePath);

        return Quality.builder()
            .inquiry(inquiry)
            .qualityReviewInfo(qualityReviewInfoEntity)
            .qualityComments(qualityComments)
            .build();
    }
}

