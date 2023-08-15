package com.medium.shazinsadakath.timeout.budgeting.controller;

import com.medium.shazinsadakath.timeout.budgeting.dto.UserDto;
import com.medium.shazinsadakath.timeout.budgeting.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final PaymentService paymentService;

    public UserController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUser(String userId) {
        UserDto userDto = new UserDto("shazin", paymentService.getPayments(userId));

        return userDto;
    }
}
