package com.magoya.challenge.application.port.out.account;

import java.math.BigDecimal;



public record CreateAccountResponse (
    String id,
    String owner,
    BigDecimal balance,
    String accountNumber
) {}
