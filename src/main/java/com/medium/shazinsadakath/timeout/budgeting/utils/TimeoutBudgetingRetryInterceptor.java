package com.medium.shazinsadakath.timeout.budgeting.utils;

import com.medium.shazinsadakath.timeout.budgeting.exception.ApiException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class TimeoutBudgetingRetryInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeoutBudgetingRetryInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (TimeoutBudgetingContext.getContext().isTimeoutBudgetExceeded()) {
            LOGGER.info("Timeout Budget Exceed");
            return null;
        }
        LOGGER.info("No Timeout Budget Applied");
        return invocation.proceed();
    }

}
