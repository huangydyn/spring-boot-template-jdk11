package com.template.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 优雅关闭
 */
@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer,
        ApplicationListener<ContextClosedEvent> {

    private volatile Connector connector;

    /**
     * 30s强制关闭
     */
    private static final int TIMEOUT = 30;

    /**
     * 自定义链接
     *
     * @param connector
     */
    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    /**
     * 关闭时触发
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("[Signal Term] Tomcat receive shutdown event");
        if (this.connector == null) {
            return;
        }
        this.connector.pause();
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    log.warn("[Signal Term] Tomcat thread pool did not shut down gracefully " +
                            "within " + "30 seconds. Proceeding with forceful shutdown");
                }
                log.info("[Signal Term] Tomcat shutdown successful");
            } catch (InterruptedException ex) {
                log.info("[Signal Term] Tomcat shutdown with exception", ex);
                Thread.currentThread().interrupt();
            }
        }
    }
}
