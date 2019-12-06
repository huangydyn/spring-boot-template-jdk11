package com.template.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ControllerLogAspect {

    private static final int MAX_ARGUMENT_LENGTH = 200;

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void logControllerMethodEntry(JoinPoint joinPoint) {
        String arguments = Arrays.toString(joinPoint.getArgs());
        if (arguments.length() > MAX_ARGUMENT_LENGTH) {
            arguments = arguments.substring(0, MAX_ARGUMENT_LENGTH);
        }
        log.info("[Api] request start , method: {}, args: {}", joinPoint.getSignature().getName(), arguments);
    }

    @After("@within(org.springframework.web.bind.annotation.RestController)")
    public void logControllerMethodEnding(JoinPoint joinPoint) {
        log.info("[Api] request end , method: {}", joinPoint.getSignature().getName());
    }
}

