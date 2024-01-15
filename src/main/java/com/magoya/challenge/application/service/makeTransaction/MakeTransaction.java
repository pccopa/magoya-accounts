package com.magoya.challenge.application.service.makeTransaction;

import com.magoya.challenge.domain.Account;

import java.math.BigDecimal;

public interface MakeTransaction {

    BigDecimal execute(Account account, BigDecimal amount);
}
