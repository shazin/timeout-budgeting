package com.medium.shazinsadakath.timeout.budgeting.utils;

import com.medium.shazinsadakath.timeout.budgeting.exception.ApiException;

public class TimeoutBudgetingContext {

    public static final String TIMEOUT_BUDGET_SECONDS_HEADER = "TIMEOUT_BUDGET_SECONDS";

    public static final String REQUEST_START_MILLIS_HEADER = "REQUEST_START_MILLIS";

    private ThreadLocal<Long> timeoutBudgetSeconds = new ThreadLocal<>();

    private ThreadLocal<Long> requestStartMillis = new ThreadLocal<>();

    private static final TimeoutBudgetingContext CONTEXT = new TimeoutBudgetingContext();

    public static final TimeoutBudgetingContext getContext() {
        return CONTEXT;
    }

    public void setTimeoutBudgetSeconds(Long timeoutBudgetSeconds) {
        this.timeoutBudgetSeconds.set(timeoutBudgetSeconds);
    }

    public Long getTimeoutBudgetSeconds() {
        return this.timeoutBudgetSeconds.get();
    }

    public void setRequestStartMillis(Long requestStartMillis) {
        this.requestStartMillis.set(requestStartMillis);
    }

    public Long getRequestStartMillis() {
        return this.requestStartMillis.get();
    }

    public boolean isTimeoutBudgetExceeded() {
        if (requestStartMillis.get() != null && timeoutBudgetSeconds.get() != null) {
            if (((System.currentTimeMillis() - requestStartMillis.get()) / 1000) > timeoutBudgetSeconds.get()) {
                return true;
            }
        }
        return false;
    }

}
