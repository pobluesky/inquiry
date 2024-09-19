package com.pobluesky.offersheet.controller;

import com.pobluesky.global.util.ResponseFactory;
import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.offersheet.dto.request.OfferSheetCreateRequestDTO;
import com.pobluesky.offersheet.dto.request.OfferSheetUpdateRequestDTO;
import com.pobluesky.offersheet.dto.response.OfferSheetResponseDTO;
import com.pobluesky.offersheet.service.OfferSheetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offersheet")
public class OfferSheetController {

    private final OfferSheetService offerSheetService;

    @Operation(
        summary = "inquiryId 별 offersheet 작성",
        description = "판매 담당자만 offersheet를 작성할 수 있다."
    )
    @PostMapping("/{inquiryId}")
    public ResponseEntity<JsonResult> createOfferSheet(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @RequestBody OfferSheetCreateRequestDTO osCreateRequestDTO
    ) {
        OfferSheetResponseDTO response = offerSheetService.createOfferSheet(
            token,
            inquiryId,
            osCreateRequestDTO
        );

        return ResponseEntity.status(HttpStatus.OK)

            .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @Operation(
        summary = "inquiryId 별 offersheet 조회",
        description = "담당자 혹은 본인의 inquiry 일때만 조회가 가능하다."
    )
    @GetMapping("/{inquiryId}")
    public ResponseEntity<JsonResult> getOfferSheetByInquiryId(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId
    ) {
        OfferSheetResponseDTO response = offerSheetService.getOfferSheetByInquiryId(
            token,
            inquiryId
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseFactory.getSuccessJsonResult(response));
    }

    @Operation(summary = "inquiryId 별 offersheet 수정", description = "담당자만 수정이 가능하다.")
    @PutMapping("/{inquiryId}")
    public ResponseEntity<JsonResult> updateOfferSheetByInquiryId(
        @RequestHeader("Authorization") String token,
        @PathVariable Long inquiryId,
        @RequestBody OfferSheetUpdateRequestDTO offerSheetUpdateRequestDTO
    ) {
        OfferSheetResponseDTO response = offerSheetService.updateOfferSheetByInquiryId(
            token,
            inquiryId,
            offerSheetUpdateRequestDTO
        );

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ResponseFactory.getSuccessJsonResult(response));
    }

}
