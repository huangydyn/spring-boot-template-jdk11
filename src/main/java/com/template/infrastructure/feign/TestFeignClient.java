package com.template.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test", url = "http://localhost:8080")
public interface TestFeignClient {

    @GetMapping("/actuator/health")
    String check();
}
