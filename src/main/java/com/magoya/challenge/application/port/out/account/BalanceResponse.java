package com.magoya.challenge.application.port.out.account;

import java.math.BigDecimal;

public record BalanceResponse(
        String accountId,
        BigDecimal balance

)
{}
