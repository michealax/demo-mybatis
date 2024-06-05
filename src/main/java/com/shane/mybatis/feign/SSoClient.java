package com.shane.mybatis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "${app.feign.client.payment.name}")
public interface SSoClient {

    @GetMapping("${app.feign.client.payment.urls[0]}")
    ResponseEntity<String> hello();
}
