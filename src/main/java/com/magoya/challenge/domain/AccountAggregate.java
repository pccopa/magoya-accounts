package com.magoya.challenge.domain;

import com.magoya.challenge.domain.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

public class AccountAggregate {

    BigDecimal balance;
    String alias;

    public AccountAggregate(BigDecimal balance, String alias ) {
        this.balance = balance;
        this.alias = alias;
    }

    Boolean isBalanceGreaterThan(BigDecimal anotherAmount) {
        return this.balance.compareTo(anotherAmount) >= 0;
    }

    void deposit (BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    void whitdrawal (BigDecimal amount) {
        if (!this.isBalanceGreaterThan(amount)) {
            throw new InsufficientBalanceException("Not enough balance to make a withdrawal");
        }
        this.balance = this.balance.subtract(amount);
    }

    BigDecimal getBalance() {
        return balance;
    }

    String getAlias() {
        return alias;
    }
}
