package com.medium.shazinsadakath.timeout.budgeting.utils;

import org.springframework.retry.annotation.Retryable;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Retryable(interceptor = "timeoutBudgetingRetryInterceptor")
public @interface TimeoutBudgetingRetryable {
}
