package com.pobluesky.inquiry.controller;

import com.pobluesky.feign.ChatClient;
import com.pobluesky.global.util.ResponseFactory;
import com.pobluesky.global.util.model.CommonResult;
import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.inquiry.dto.request.InquiryCreateRequestDTO;
import com.pobluesky.inquiry.dto.request.InquiryUpdateRequestDTO;
import com.pobluesky.inquiry.dto.response.InquiryAllocateResponseDTO;
import com.pobluesky.inquiry.dto.response.InquiryFavoriteLineItemResponseDTO;
import com.pobluesky.inquiry.dto.response.InquiryFavoriteResponseDTO;
import com.pobluesky.inquiry.dto.response.InquiryLogResponseDTO;
import com.pobluesky.inquiry.dto.response.InquiryProgressResponseDTO;
import com.pobluesky.inquiry.dto.response.InquiryResponseDTO;
import com.pobluesky.inquiry.dto.response.InquirySummaryResponseDTO;
import com.pobluesky.inquiry.entity.Industry;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.entity.InquiryType;
import com.pobluesky.inquiry.entity.ProductType;
import com.pobluesky.inquiry.entity.Progress;
import com.pobluesky.inquiry.service.InquiryService;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    private final ChatClient chatClient;

    // msa에 사용할 존재 여부만 확인하는 간단한 API 추가
    @GetMapping("/exists/{inquiryId}")
    @Operation(summary = "Inquiry 존재여부)", description = "inquiry가 존재하는지 bool 출력")
    public ResponseEntity<Boolean> checkInquiryExists(@PathVariable("inquiryId") Long inquiryId) {
        boolean exists = inquiryService.existsById(inquiryId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/without-token/{inquiryId}")
    @Operation(summary = "Inquiry 토큰 없이 가져오기", description = "Inquiry 토큰 없이 가져오기")
    public ResponseEntity<JsonResult> getInquiryByIdWithoutToken(@PathVariable("inquiryId") Long inquiryId) {
        Inquiry inquiry = inquiryService.getInquiryByIdWithoutToken(inquiryId);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(inquiry));
    }


    @GetMapping("/customers/inquiries/{userId}")
    @Operation(summary = "Inquiry 조회(고객사)", description = "등록된 모든 Inquiry를 조건에 맞게 조회한다.")
    public ResponseEntity<JsonResult> getInquiriesByCustomer(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @RequestParam(defaultValue = "LATEST") String sortBy,
        @RequestParam(required = false) Progress progress,
        @RequestParam(required = false) ProductType productType,
        @RequestParam(required = false) String customerName,
        @RequestParam(required = false) InquiryType inquiryType,
        @RequestParam(required = false) String salesPerson,
        @RequestParam(required = false) Industry industry,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @RequestParam(required = false) String salesManagerName,
        @RequestParam(required = false) String qualityManagerName
    ) {
        List<InquirySummaryResponseDTO> inquiries = inquiryService.getInquiriesByCustomer(
            token,
            userId,
            sortBy,
            progress,
            productType,
            customerName,
            inquiryType,
            salesPerson,
            industry,
            startDate,
            endDate,
            salesManagerName,
            qualityManagerName
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(inquiries));
    }

    // 상세 조회 페이지
    @GetMapping("/customers/inquiries/{userId}/{inquiryId}")
    @Operation(summary = "고객사 Inquiry 상세 조회")
    public ResponseEntity<JsonResult> getInquiryDetail(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @PathVariable Long inquiryId
    ) {
        InquiryResponseDTO response = inquiryService.getInquiryDetailForCustomer(
            token,
            userId,
            inquiryId
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @PostMapping("/customers/inquiries/{userId}")
    @Operation(summary = "고객사 Inquiry 생성")
    public ResponseEntity<JsonResult> createInquiry(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @RequestPart(value = "files", required = false) MultipartFile file,
        @RequestPart("inquiry") InquiryCreateRequestDTO dto) {
        InquiryResponseDTO response = inquiryService.createInquiry(
            token,
            userId,
            file,
            dto
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @PutMapping("/customers/inquiries/{inquiryId}")
    @Operation(summary = "고객사 Inquiry 수정")
    public ResponseEntity<JsonResult> updateInquiryById(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @RequestPart(value = "files", required = false) MultipartFile file,
        @RequestPart("inquiry") InquiryUpdateRequestDTO inquiryUpdateRequestDTO
    ) {
        InquiryResponseDTO response = inquiryService.updateInquiryById(
            token,
            inquiryId,
            file,
            inquiryUpdateRequestDTO
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @DeleteMapping("/customers/inquiries/{inquiryId}")
    @Operation(summary = "고객사 Inquiry 삭제")
    public ResponseEntity<CommonResult> deleteInquiryById(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        inquiryService.deleteInquiryById(token, inquiryId);

        return ResponseEntity.ok(ResponseFactory.getSuccessResult());
    }

    // 담당자 Inquiry 조회
    @GetMapping("/managers/sales/inquiries")
    @Operation(summary = "Inquiry 조회(담당자)", description = "등록된 모든 Inquiry를 조건에 맞게 조회한다.")
    public ResponseEntity<JsonResult> getInquiriesBySalesManager(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "LATEST") String sortBy,
        @RequestParam(required = false) Progress progress,
        @RequestParam(required = false) ProductType productType,
        @RequestParam(required = false) String customerName,
        @RequestParam(required = false) InquiryType inquiryType,
        @RequestParam(required = false) String salesPerson,
        @RequestParam(required = false) Industry industry,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @RequestParam(required = false) String salesManagerName,
        @RequestParam(required = false) String qualityManagerName
    ) {
        List<InquirySummaryResponseDTO> inquiries = inquiryService.getInquiriesBySalesManager(
            token,
            sortBy,
            progress,
            productType,
            customerName,
            inquiryType,
            salesPerson,
            industry,
            startDate,
            endDate,
            salesManagerName,
            qualityManagerName
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(inquiries));
    }

    @GetMapping("/managers/quality/inquiries")
    @Operation(summary = "Inquiry 조회(담당자)", description = "등록된 모든 Inquiry를 조건에 맞게 조회한다.")
    public ResponseEntity<JsonResult> getInquiriesByQualityManager(
        @RequestHeader("Authorization") String token,
        @RequestParam(defaultValue = "LATEST") String sortBy,
        @RequestParam(required = false) Progress progress,
        @RequestParam(required = false) ProductType productType,
        @RequestParam(required = false) String customerName,
        @RequestParam(required = false) String salesPerson,
        @RequestParam(required = false) Industry industry,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
        @RequestParam(required = false) String salesManagerName,
        @RequestParam(required = false) String qualityManagerName
    ) {
        List<InquirySummaryResponseDTO> inquiries = inquiryService.getInquiriesByQualityManager(
            token,
            sortBy,
            progress,
            productType,
            customerName,
            salesPerson,
            industry,
            startDate,
            endDate,
            salesManagerName,
            qualityManagerName
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(inquiries));
    }

    // 상세 조회 페이지
    @GetMapping("/managers/inquiries/{inquiryId}")
    @Operation(summary = "담당자 Inquiry 상세 조회")
    public ResponseEntity<JsonResult> getInquiryDetailForManager(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        InquiryResponseDTO response = inquiryService.getInquiryDetailForManager(
            token,
            inquiryId
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @PutMapping("/managers/inquiries/{inquiryId}/progress/{progress}")
    @Operation(summary = "담당자 Inquiry 상태 업데이트")
    public ResponseEntity<InquiryProgressResponseDTO> updateInquiryProgress(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @PathVariable String progress
    ) {
        InquiryProgressResponseDTO response = inquiryService.updateInquiryProgress(
            token,
            inquiryId,
            progress
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/managers/inquiries/{inquiryId}/allocate/{qualityManagerId}")
    @Operation(summary = "Quality Manager Inquiry 배정", description = "품질+견적 유형에 대한 Inquiry 품질 담당자를 판매 담당자가 배정한다.")
    public ResponseEntity <InquiryAllocateResponseDTO> allocateQualityManager(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @PathVariable Long qualityManagerId
    ) {
        InquiryAllocateResponseDTO response = inquiryService.allocateQualityManager(token, inquiryId, qualityManagerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("customers/inquiries/{userId}/{productType}/all")
    @Operation(summary = "제품 유형에 따른 고객의 전체 Inquiry 목록 조회")
    public ResponseEntity<JsonResult> getAllInquiriesByProductType(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @PathVariable ProductType productType
    ) {
        List<InquiryFavoriteResponseDTO> response =
            inquiryService.getAllInquiriesByProductType(
                    token,
                    userId,
                    productType
            );

        return ResponseEntity.ok(ResponseFactory.getSuccessJsonResult(response));
    }

    @GetMapping("customers/inquiries/{userId}/{productType}/favorite")
    @Operation(summary = "제품 유형에 따른 고객의 즐겨찾기 Inquiry 목록 조회")
    public ResponseEntity<JsonResult> getFavoriteInquiriesByProductType(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @PathVariable ProductType productType
    ) {
        List<InquiryFavoriteResponseDTO> response =
            inquiryService.getFavoriteInquiriesByProductType(
                    token,
                    userId,
                    productType
            );

        return ResponseEntity.ok(ResponseFactory.getSuccessJsonResult(response));
    }

    @PutMapping("customers/inquiries/{inquiryId}/favorite")
    @Operation(summary = "고객사가 전체 Inquiry 목록 중 즐겨찾기할 경우 상태 업데이트")
    public ResponseEntity<CommonResult> updateFavoriteInquiryStatus(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        inquiryService.updateFavoriteInquiryStatus(token, inquiryId);

        return ResponseEntity.ok(ResponseFactory.getSuccessResult());
    }

    @GetMapping("customers/inquiries/{userId}/{inquiryId}/line-items")
    @Operation(summary = "특정 inquiryId에 해당하는 라인 아이템 조회")
    public ResponseEntity<JsonResult> getLineItemsByInquiryId(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @PathVariable Long inquiryId
    ) {
        InquiryFavoriteLineItemResponseDTO response = inquiryService.getLineItemsByInquiryId(
                token,
                userId,
                inquiryId
        );

        return ResponseEntity.ok(ResponseFactory.getSuccessJsonResult(response));
    }

    @PostMapping("/customers/inquiries/{userId}/optimized")
    @Operation(summary = "제품 유형별 라인아이템 등록 최적화")
    public ResponseEntity<JsonResult<?>> processOcrAndChatGpt(
        @RequestHeader("Authorization") String token,
        @PathVariable Long userId,
        @RequestPart(value = "files", required = false) MultipartFile file,
        @RequestParam("productType") ProductType productType
    ) {
        JsonResult<?> response = chatClient.processOcrFile(token,userId,file,productType);

        return ResponseEntity.ok(response);
    }

    /* [Start] Dashboard API */
    @GetMapping("/managers/inquiries/dashboard/average-monthly")
    @Operation(summary = "월별 Inquiry 주문 처리 소요일 평균")
    public ResponseEntity<Map<String, List<Object[]>>> averageMonthlyInquiry(
        @RequestHeader("Authorization") String token
    ) {
        Map<String, List<Object[]>> response = inquiryService.getAverageDaysPerMonth(token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/managers/inquiries/dashboard/counts-by-progress")
    @Operation(summary = "전체 Inquiry 검토 현황별 건수")
    public ResponseEntity<Map<String, List<Object[]>>> getInquiryCountsByManager(
        @RequestHeader("Authorization") String token
    ) {
        Map<String, List<Object[]>> response = inquiryService.getInquiryCountsByProgress(token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/managers/inquiries/dashboard/percentage-completed-uncompleted")
    @Operation(summary = "Inquiry 주문 완료 및 미완료 비중")
    public ResponseEntity<Map<String, Map<String, String>>> getInquiryPercentageCompletedUncompleted(
        @RequestHeader("Authorization") String token
    ) {
        Map<String, Map<String, String>> response = inquiryService.getInquiryPercentageCompletedUncompleted(token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/managers/inquiries/dashboard/counts-by-productType")
    @Operation(summary = "전체 제품별 주문 처리 현황")
    public ResponseEntity<Map<String, List<Object[]>>> getInquiryCountsByProductType(
        @RequestHeader("Authorization") String token
    ) {
        Map<String, List<Object[]>> response = inquiryService.getInquiryCountsByProductType(token);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/managers/inquiries/dashboard/counts-by-department")
    @Operation(summary = "부서별 월별 총 문의 건수")
    public ResponseEntity<Map<String, List<Object[]>>> getInquiryCountsByDepartment(
        @RequestHeader("Authorization") String token,
        @RequestParam(value = "date", required = false) String date
    ) {
        Map<String, List<Object[]>> response = inquiryService.getInquiryCountsByDepartment(token, date);
        return ResponseEntity.ok(response);
    }

    @GetMapping("managers/inquiries/dashboard/{inquiryId}/logs")
    @Operation(summary = "Inquiry 진행 상태 히스토리 조회")
    public ResponseEntity<JsonResult> getInquiryLogs(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        InquiryLogResponseDTO response = inquiryService.getInquiryLogs(token, inquiryId);
        return ResponseEntity.ok(ResponseFactory.getSuccessJsonResult(response));
    }

    /* [End] Dashboard API */
}
