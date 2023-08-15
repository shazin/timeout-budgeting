package com.medium.shazinsadakath.timeout.budgeting.service;

import com.medium.shazinsadakath.timeout.budgeting.dto.PaymentDto;
import com.medium.shazinsadakath.timeout.budgeting.exception.ApiException;
import com.medium.shazinsadakath.timeout.budgeting.utils.TimeoutBudgetingRetryable;

import java.util.List;

public interface PaymentService {

    @TimeoutBudgetingRetryable
    List<PaymentDto> getPayments(String userId) throws ApiException;
}
