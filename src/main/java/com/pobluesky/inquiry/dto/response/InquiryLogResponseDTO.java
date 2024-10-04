package com.pobluesky.inquiry.dto.response;

import com.pobluesky.inquiry.entity.InquiryLog;
import com.pobluesky.inquiry.entity.Progress;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InquiryLogResponseDTO {
    private Long inquiryId;
    private List<ProgressLog> logs;

    @Getter
    @Builder
    public static class ProgressLog {
        private Progress progress;
        private LocalDateTime timestamp;
    }

    public static InquiryLogResponseDTO from(Long inquiryId, List<InquiryLog> logs) {
        List<ProgressLog> logItems = logs.stream()
            .map(log -> ProgressLog.builder()
                .progress(log.getProgress())
                .timestamp(log.getCreatedDate())
                .build())
            .collect(Collectors.toList());

        return InquiryLogResponseDTO.builder()
            .inquiryId(inquiryId)
            .logs(logItems)
            .build();
    }
}
