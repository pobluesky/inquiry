package com.pobluesky.quality.service;

import com.pobluesky.feign.FileClient;
import com.pobluesky.feign.Manager;
import com.pobluesky.feign.UserClient;
import com.pobluesky.feign.FileInfo;
import com.pobluesky.global.error.CommonException;
import com.pobluesky.global.error.ErrorCode;
import com.pobluesky.global.security.UserRole;
import com.pobluesky.inquiry.entity.Inquiry;
import com.pobluesky.inquiry.repository.InquiryRepository;
import com.pobluesky.quality.dto.request.QualityCreateRequestDTO;
import com.pobluesky.quality.dto.response.QualityResponseDTO;
import com.pobluesky.quality.entity.Quality;
import com.pobluesky.quality.repository.QualityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class QualityService {

    private final QualityRepository qualityRepository;

    private final InquiryRepository inquiryRepository;

    private final UserClient userClient;

    private final FileClient fileClient;

    @Transactional(readOnly = true)
    public List<QualityResponseDTO> getAllQualities(String token) {
        Long userId = userClient.parseToken(token);

        if(!userClient.managerExists(userId)){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        List<Quality> qualities = qualityRepository.findAll();

        return qualities.stream()
            .map(QualityResponseDTO::from)
            .collect(Collectors.toList());
    }

    @Transactional
    public QualityResponseDTO createQuality(
        String token,
        QualityCreateRequestDTO dto,
        MultipartFile file,
        Long inquiryId
    ) {
        Long userId = userClient.parseToken(token);

        Manager manager = userClient.getManagerByIdWithoutToken(userId).getData();
        if(manager == null){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        if(manager.getRole() != UserRole.QUALITY)
            throw new CommonException(ErrorCode.UNAUTHORIZED_USER_QUALITY);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        if(qualityRepository.existsByInquiry(inquiry)) {
            throw new CommonException(ErrorCode.QUALITY_ALREADY_EXISTS);
        }

        String fileName = null;
        String filePath = null;

        if (file != null) {
            FileInfo fileInfo = fileClient.uploadFile(file);
            fileName = fileInfo.getOriginName();
            filePath = fileInfo.getStoredFilePath();
        }

        Quality quality = dto.toQualityEntity(inquiry, fileName, filePath);
        Quality savedQuality = qualityRepository.save(quality);

        return QualityResponseDTO.from(savedQuality);
    }

    @Transactional(readOnly = true)
    public QualityResponseDTO getReviewByInquiry(String token, Long inquiryId) {
        Long userId = userClient.parseToken(token);

        if(!userClient.managerExists(userId)){
            throw new CommonException(ErrorCode.USER_NOT_FOUND);
        }

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
            .orElseThrow(() -> new CommonException(ErrorCode.INQUIRY_NOT_FOUND));

        Quality quality = qualityRepository.findByInquiry(inquiry)
            .orElseThrow(() -> new CommonException(ErrorCode.QUALITY_NOT_FOUND));

        return QualityResponseDTO.from(quality);
    }
}
