package com.template;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@SuppressWarnings("PMD.UseUtilityClass")
@EnableFeignClients(basePackages = "com.template")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

