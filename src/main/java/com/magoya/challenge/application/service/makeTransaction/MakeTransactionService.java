package com.magoya.challenge.application.service.makeTransaction;

import com.magoya.challenge.application.port.in.makeTransaction.MakeTransactionCommand;
import com.magoya.challenge.application.port.in.makeTransaction.MakeTransactionPort;
import com.magoya.challenge.application.port.in.makeTransaction.TransactionAbstractFactory;
import com.magoya.challenge.application.port.out.account.FindAccountPort;
import com.magoya.challenge.application.port.out.account.UpdateAccountPort;
import com.magoya.challenge.application.port.out.account.BalanceResponse;
import com.magoya.challenge.application.port.out.events.EventPublishPort;
import com.magoya.challenge.application.port.out.events.EventType;
import com.magoya.challenge.common.UseCase;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.TransactionAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class MakeTransactionService implements MakeTransactionPort {

    private final FindAccountPort findAccountPort;
    private final UpdateAccountPort updateAccountPort;
    private final EventPublishPort<TransactionAggregate> publishPort;

    /**
     * Executes Use Case of transaction. Based on {@link com.magoya.challenge.domain.TransactionType}
     * will decide if needed a deposit or withdrawal service, then executes logic.
     * After execution save a {@link TransactionAggregate} of {@link Account} and publish
     * an event in queue
     * @param command with all information to execute correct logic
     * @return BalanceResponse with corresponding information of useCase
     */
    @Override
    @Transactional
    public BalanceResponse execute(MakeTransactionCommand command) {

        Account account = findAccountPort.findById(command.getAccountId());
        var service = TransactionAbstractFactory.getTransactionUseCase(command.getTxType());

        BigDecimal result = service.execute(account, command.getAmount());
        account.addTransaction(new TransactionAggregate(command.getAmount(), command.getTxType()));

        updateAccountPort.update(account);
        publishPort.publish(account.getLastTransaction(), EventType.CREATE);
        return new BalanceResponse(command.getAccountId(), result);
    }

}
