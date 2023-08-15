package com.medium.shazinsadakath.timeout.budgeting.dto;

import java.io.Serializable;
import java.util.Date;

public record PaymentDto(Double amount, String paidOn, String type) implements Serializable {


}
