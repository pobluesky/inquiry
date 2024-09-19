package com.pobluesky.quality.controller;

import com.pobluesky.global.util.ResponseFactory;
import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.quality.dto.request.QualityCreateRequestDTO;
import com.pobluesky.quality.dto.response.QualityResponseDTO;
import com.pobluesky.quality.service.QualityService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/qualities")
public class QualityController {

    private final QualityService qualityService;

    @Operation(summary = "품질검토 전체 조회")
    @GetMapping
    public ResponseEntity<JsonResult> getQualities(@RequestHeader("Authorization") String token) {
        List<QualityResponseDTO> response = qualityService.getAllQualities(token);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @Operation(summary = "품질검토 조회", description = "품질 검토는 담당자만 조회가 가능하다.")
    @GetMapping("/{inquiryId}")
    public ResponseEntity<JsonResult> getReviewByInquiry(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        QualityResponseDTO response = qualityService.getReviewByInquiry(token, inquiryId);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @Operation(summary = "inquiryId 별 품질검토 작성")
    @PostMapping("/{inquiryId}")
    public ResponseEntity<JsonResult> createQuality(
        @RequestHeader("Authorization") String token,
        @RequestPart("quality") QualityCreateRequestDTO dto,
        @RequestPart(value = "files", required = false) MultipartFile file,
        @PathVariable Long inquiryId) {
        QualityResponseDTO response = qualityService.createQuality(
            token,
            dto,
            file,
            inquiryId
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }
}
