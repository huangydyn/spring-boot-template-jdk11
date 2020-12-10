package com.template.representation;

import com.template.infrastructure.feign.TestFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    private final TestFeignClient testFeignClient;

    public HelloController(TestFeignClient testFeignClient) {
        this.testFeignClient = testFeignClient;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring 2.1 with Jdk11";
    }

    @GetMapping("/check")
    public String check() {
        log.info("current thread: {}", Thread.currentThread().getName());
        return testFeignClient.check();
    }
}
