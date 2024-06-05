package com.shane.mybatis.feign;

import com.shane.mybatis.feign.fallback.SsoFallback;
import com.shane.mybatis.feign.fallback.SsoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "${app.feign.client.payment.name}", fallbackFactory = SsoFallbackFactory.class)
public interface SSoClient {

    @GetMapping("${app.feign.client.payment.urls[0]}")
    ResponseEntity<String> hello();

    @GetMapping("${app.feign.client.payment.urls[1]}")
    ResponseEntity<String> error();
}
