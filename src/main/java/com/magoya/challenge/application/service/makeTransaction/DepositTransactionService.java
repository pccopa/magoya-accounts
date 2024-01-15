package com.magoya.challenge.application.service.makeTransaction;

import com.magoya.challenge.application.port.out.events.EventPublishPort;
import com.magoya.challenge.application.port.out.events.EventType;
import com.magoya.challenge.common.UseCase;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.TransactionAggregate;
import com.magoya.challenge.domain.TransactionType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@UseCase("DEPOSIT")
@RequiredArgsConstructor
public class DepositTransactionService implements MakeTransaction {
    private final EventPublishPort<TransactionAggregate> publishPort;

    /**
     * Only execute a deposit in a domain context
     * @param account its the exact account to execute business logic
     * @param amount amount to be added to current account
     * @return new balance of account
     */
    @Override
    public BigDecimal execute(Account account, BigDecimal amount) {
        if (amount.compareTo(new BigDecimal(10000)) > 0) {
            TransactionAggregate aggregate = new TransactionAggregate(amount, TransactionType.DEPOSIT);
            publishPort.publish(aggregate, EventType.GREATER_THAN_10000);
        }
        return account.deposit(amount);
    }
}
