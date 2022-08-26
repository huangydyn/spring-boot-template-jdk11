package com.template;


import com.template.configuration.GracefulShutdown;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@SuppressWarnings("PMD.UseUtilityClass")
@EnableFeignClients(basePackages = "com.template")
public class Application {

    @Bean("gracefulShutdown")
    public GracefulShutdown gracefulShutdown() {
        return new GracefulShutdown();
    }

    /**
     * tomcat配置优雅关闭
     * @param gracefulShutdown
     * @return
     */
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(@Qualifier("gracefulShutdown") GracefulShutdown gracefulShutdown) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(gracefulShutdown);
        return factory;
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

