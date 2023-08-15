package com.medium.shazinsadakath.timeout.budgeting.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetingContext.REQUEST_START_MILLIS_HEADER;
import static com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetingContext.TIMEOUT_BUDGET_SECONDS_HEADER;

@Component
public class TimeoutBudgetingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutBudgetingFilter.class);

    private static final String TIMEOUT_BUDGET_FILTER_DISABLED_HEADER = "TIMEOUT_BUDGET_FILTER_DISABLED";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Filter initialized");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Boolean filterEnabled = true;
        if (request instanceof HttpServletRequest httpServletRequest) {
            LOGGER.info("Filter invoked");
            if (httpServletRequest.getHeader(TIMEOUT_BUDGET_SECONDS_HEADER) != null) {
                TimeoutBudgetingContext.getContext().setTimeoutBudgetSeconds(getValue(TIMEOUT_BUDGET_SECONDS_HEADER, httpServletRequest.getHeader(TIMEOUT_BUDGET_SECONDS_HEADER)));
            }
            if (httpServletRequest.getHeader(REQUEST_START_MILLIS_HEADER) != null) {
                TimeoutBudgetingContext.getContext().setRequestStartMillis(getValue(REQUEST_START_MILLIS_HEADER, httpServletRequest.getHeader(REQUEST_START_MILLIS_HEADER)));
            }
            if (httpServletRequest.getHeader(TIMEOUT_BUDGET_FILTER_DISABLED_HEADER) != null) {
                filterEnabled = false;
            }
            LOGGER.info("Filter invocation done");
        }
        if (filterEnabled && TimeoutBudgetingContext.getContext().isTimeoutBudgetExceeded()) {
            LOGGER.info("Timeout Budget Exceeded");
            if (response instanceof HttpServletResponse httpServletResponse) {
                httpServletResponse.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getOutputStream().write("{\"message\":\"Timeout Budget Exceeded\"}".getBytes(StandardCharsets.UTF_8));
                httpServletResponse.getOutputStream().flush();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("Filter destroyed");
        Filter.super.destroy();
    }

    private Long getValue(String name, String input) {
        try {
            Long value = Long.parseLong(input);
            LOGGER.info("{} value set for {}", value, name);
            return value;
        } catch (NumberFormatException e) {
            LOGGER.error("Error while reading long value for {}", name, e);
            return null;
        }
    }
}
