package com.template.representation;

import com.template.representation.repo.UserRepo;
import com.template.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello() throws Exception {
        helloService.hello();
        return "hello";
    }
}
