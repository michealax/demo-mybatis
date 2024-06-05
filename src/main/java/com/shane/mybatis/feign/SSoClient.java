package com.shane.mybatis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "payment")
public interface SSoClient {

    //    @GetMapping("${feign.client.config.sso-server[0]}")
    @GetMapping("/payment/hello")
    ResponseEntity<String> hello();
}
