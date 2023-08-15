package com.medium.shazinsadakath.timeout.budgeting.dto;

import java.io.Serializable;
import java.util.List;

public record UserDto(String userName, List paymentHistory) implements Serializable {

}
