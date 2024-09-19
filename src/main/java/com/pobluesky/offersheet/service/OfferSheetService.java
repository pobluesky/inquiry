package com.pobluesky.offersheet.service;

import com.pobluesky.feign.Customer;
import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.global.error.CommonException;
import com.pobluesky.global.error.ErrorCode;
import com.pobluesky.global.security.UserRole;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.repository.InquiryRepository;
import com.pobluesky.offersheet.dto.request.OfferSheetCreateRequestDTO;
import com.pobluesky.offersheet.dto.request.OfferSheetUpdateRequestDTO;
import com.pobluesky.offersheet.dto.response.OfferSheetResponseDTO;
import com.pobluesky.offersheet.entity.OfferSheet;
import com.pobluesky.offersheet.repository.OfferSheetRepository;
import com.pobluesky.receipt.entity.Receipt;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OfferSheetService {

    private final OfferSheetRepository offerSheetRepository;

    private final InquiryRepository inquiryRepository;

    private final UserClient userClient;

    @Transactional(readOnly = true)
    public OfferSheetResponseDTO getOfferSheetByInquiryId(String token, Long inquiryId) {
        Long userId = userClient.parseToken(token);

        OfferSheet offerSheet = offerSheetRepository.findByInquiryInquiryId(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.OFFERSHEET_NOT_FOUND));

        if (!userClient.managerExists(userId)) {
            Customer customer = userClient.getCustomerByIdWithoutToken(userId).getData();
            if(customer == null){
                throw new CommonException(ErrorCode.USER_NOT_FOUND);
            }

            if(!Objects.equals(customer.getUserId(), offerSheet.getInquiry().getUserId()))
                throw new CommonException(ErrorCode.USER_NOT_MATCHED);
        }

        return OfferSheetResponseDTO.from(offerSheet,userClient);
    }

    @Transactional
    public OfferSheetResponseDTO updateOfferSheetByInquiryId(
        String token,
        Long inquiryId,
        OfferSheetUpdateRequestDTO offerSheetUpdateRequestDTO
    ) {
        Long userId = userClient.parseToken(token);

        Manager manager = userClient.getManagerByIdWithoutToken(userId).getData();

        if(manager == null){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        if(manager.getRole() != UserRole.SALES) {
            throw new CommonException(ErrorCode.UNAUTHORIZED_USER_SALES);
        }

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        // 2. 해당 inquiry에 연결된 offersheet가 있는지 확인
        OfferSheet offerSheet = offerSheetRepository.findByInquiry(inquiry)
            .orElseThrow(() -> new CommonException(ErrorCode.OFFERSHEET_NOT_FOUND));

        // 3. offersheet update
        offerSheet.updateOfferSheet(
            offerSheetUpdateRequestDTO.priceTerms(),
            offerSheetUpdateRequestDTO.paymentTerms(),
            offerSheetUpdateRequestDTO.shipment(),
            offerSheetUpdateRequestDTO.validity(),
            offerSheetUpdateRequestDTO.destination(),
            offerSheetUpdateRequestDTO.remark()
        );

        //4. entity -> dto
        return OfferSheetResponseDTO.from(offerSheet,userClient);
    }

    @Transactional
    public OfferSheetResponseDTO createOfferSheet(
        String token,
        Long inquiryId,
        OfferSheetCreateRequestDTO offerSheetCreateRequestDTO
    ) {
        Long userId = userClient.parseToken(token);

        Manager manager = userClient.getManagerByIdWithoutToken(userId).getData();

        if(manager == null){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        if(manager.getRole() != UserRole.SALES) {
            throw new CommonException(ErrorCode.UNAUTHORIZED_USER_SALES);
        }

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        OfferSheet offerSheet = offerSheetCreateRequestDTO.toOfferSheetEntity(inquiry);

        List<Receipt> receipts = offerSheetCreateRequestDTO.receipts().stream()
            .map(receiptCreateRequestDTO -> receiptCreateRequestDTO.toReceiptEntity(offerSheet))
            .collect(Collectors.toList());

        offerSheet.getReceipts().addAll(receipts);

        OfferSheet savedOfferSheet = offerSheetRepository.save(offerSheet);

        return OfferSheetResponseDTO.from(savedOfferSheet,userClient);
    }
}
