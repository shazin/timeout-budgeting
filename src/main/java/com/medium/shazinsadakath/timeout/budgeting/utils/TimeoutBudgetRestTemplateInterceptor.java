package com.medium.shazinsadakath.timeout.budgeting.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetingContext.REQUEST_START_MILLIS_HEADER;
import static com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetingContext.TIMEOUT_BUDGET_SECONDS_HEADER;

public class TimeoutBudgetRestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutBudgetRestTemplateInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (TimeoutBudgetingContext.getContext().getRequestStartMillis() != null) {
            request.getHeaders().add(REQUEST_START_MILLIS_HEADER, String.valueOf(TimeoutBudgetingContext.getContext().getRequestStartMillis()));
        }
        if (TimeoutBudgetingContext.getContext().getTimeoutBudgetSeconds() != null) {
            request.getHeaders().add(TIMEOUT_BUDGET_SECONDS_HEADER, String.valueOf(TimeoutBudgetingContext.getContext().getTimeoutBudgetSeconds()));
        }
        return execution.execute(request, body);
    }

}
