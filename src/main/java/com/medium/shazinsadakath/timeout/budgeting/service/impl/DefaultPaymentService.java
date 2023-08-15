package com.medium.shazinsadakath.timeout.budgeting.service.impl;

import com.medium.shazinsadakath.timeout.budgeting.dto.PaymentDto;
import com.medium.shazinsadakath.timeout.budgeting.exception.ApiException;
import com.medium.shazinsadakath.timeout.budgeting.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class DefaultPaymentService implements PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultPaymentService.class);

    private final RestTemplate restTemplate;

    public DefaultPaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<PaymentDto> getPayments(String userId) throws ApiException {
        try {
            return restTemplate.getForObject("http://localhost:9090/payments/"+userId, List.class);
        } catch (HttpStatusCodeException e) {
            LOGGER.error("Error while getting payments", e);
            if (e.getStatusCode() == HttpStatus.REQUEST_TIMEOUT) {
                LOGGER.error("Request Timed out");
            }
            return null;
        } catch (Exception e) {
            LOGGER.error("Error while getting payments", e);
            throw new ApiException(e);
        }
    }
}
