package com.apress.todo.filter;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class ToDoMetricsFilter implements Filter {

    private final MeterRegistry meterRegistry;

    public ToDoMetricsFilter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        if ("GET".equals(req.getMethod()) && req.getRequestURI().equals("/toDos")) {
            meterRegistry.counter("api_get_total").increment();
        }
        if ("POST".equals(req.getMethod()) && req.getRequestURI().equals("/toDos")) {
            meterRegistry.counter("api_post_todos_total").increment();
        }

        chain.doFilter(request, response);
    }
}
