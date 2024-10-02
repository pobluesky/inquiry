package com.pobluesky.feign;

import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.inquiry.entity.ProductType;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "chat", configuration = FeignConfig.class)
public interface ChatClient {

    @PostMapping("/api/ocr/{userId}")
    JsonResult<Map<String, Object>> processOcrFile(
        @RequestHeader("Authorization") String token,
        @PathVariable("userId") Long userId,
        @RequestParam("file") MultipartFile file,
        @RequestParam("productType") ProductType productType
    );

}
