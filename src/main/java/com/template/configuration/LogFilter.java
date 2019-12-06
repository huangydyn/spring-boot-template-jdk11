package com.template.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@SuppressWarnings("PMD")
public class LogFilter implements Filter {

    private static final String X_REQUEST_URI = "X-REQUEST-URI";

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            MDC.put(X_REQUEST_URI, httpRequest.getRequestURI());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
