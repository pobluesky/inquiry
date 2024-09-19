package com.pobluesky.feign;


import com.pobluesky.global.util.model.JsonResult;
import com.pobluesky.inquiry.dto.response.ManagerSummaryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", configuration = FeignConfig.class)
public interface UserClient {

//    @GetMapping("/api/customers/{userId}")
//    JsonResult getCustomerById(@RequestHeader("Authorization") String token,@PathVariable("userId") Long userId);
//
//    @GetMapping("/api/managers/{userId}")
//    JsonResult getManagerById(@RequestHeader("Authorization") String token,@PathVariable("userId") Long userId);
//
//    @GetMapping("/api/managers/summary/{userId}")
//    ManagerSummaryResponseDTO getManagerSummaryById(@RequestHeader("Authorization") String token,@PathVariable("userId") Long userId);

    @GetMapping("/api/managers/summary/{userId}")
    JsonResult<ManagerSummaryResponseDTO> getManagerSummaryById(@PathVariable("userId") Long userId);

    @GetMapping("/api/customers/without-token/{userId}")
    JsonResult<Customer> getCustomerByIdWithoutToken(@PathVariable("userId") Long userId);

    @GetMapping("/api/managers/without-token/{userId}")
    JsonResult<Manager> getManagerByIdWithoutToken(@PathVariable("userId") Long userId);

    @GetMapping("/api/users/token")
    Long parseToken(@RequestParam("token") String token);

    @GetMapping("/api/managers/exists")
    Boolean managerExists(@RequestParam("userId") Long userId);

    @GetMapping("/api/customers/exists")
    Boolean customerExists(@RequestParam("userId") Long userId);

}
