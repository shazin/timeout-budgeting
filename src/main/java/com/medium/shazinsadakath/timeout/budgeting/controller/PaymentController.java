package com.medium.shazinsadakath.timeout.budgeting.controller;

import com.medium.shazinsadakath.timeout.budgeting.dto.PaymentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{userId}")
    public List<PaymentDto> getPayments(String userId) throws Exception {
        Thread.sleep(10000);
        return Arrays.asList(new PaymentDto(10.00, String.valueOf(new Date()), "Subscription"));
    }
}
