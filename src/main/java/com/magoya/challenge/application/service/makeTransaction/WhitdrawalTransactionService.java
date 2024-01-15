package com.magoya.challenge.application.service.makeTransaction;

import com.magoya.challenge.common.UseCase;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.exceptions.InsufficientBalanceException;

import java.math.BigDecimal;

@UseCase("WHITDRAWAL")
public class WhitdrawalTransactionService implements MakeTransaction {

    /**
     * Only execute a withdrawal in a domain context
     * @param account its the exact account to execute business logic
     * @param amount amount to be extracted of current account
     * @return new balance of account
     * @throws {@link InsufficientBalanceException} when current balance is lower than amount
     */
    @Override
    public BigDecimal execute(Account account, BigDecimal amount) {
        if (!account.isBalanceGreaterThan(amount)) {
            throw new InsufficientBalanceException("Not enough balance available to withdrawal");
        }
        return account.withdrawal(amount);
    }
}
