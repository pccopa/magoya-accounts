package com.magoya.challenge.application.service.account;

import com.magoya.challenge.application.port.in.createAccount.CreateAccountCommand;
import com.magoya.challenge.application.port.in.createAccount.CreateAccountPort;
import com.magoya.challenge.application.port.out.account.SaveAccountPort;
import com.magoya.challenge.application.port.out.account.CreateAccountResponse;
import com.magoya.challenge.application.port.out.events.EventPublishPort;
import com.magoya.challenge.application.port.out.events.EventType;
import com.magoya.challenge.common.UseCase;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.AccountAggregate;
import com.magoya.challenge.domain.OwnerAggregate;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@UseCase
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountPort {

    private final SaveAccountPort saveAccountPort;
    private final EventPublishPort<Account> publishPort;

    @Override
    @Transactional
    public CreateAccountResponse create(CreateAccountCommand command) {
        Account account = new Account();

        AccountAggregate accountAggregate = new AccountAggregate(BigDecimal.ZERO, command.getAliasAccount());
        OwnerAggregate ownerAggregate = new OwnerAggregate(command.getOwnerName());

        account.setAccount(accountAggregate);
        account.setOwner(ownerAggregate);

        Account savedAccount = saveAccountPort.save(account);
        publishPort.publish(savedAccount, EventType.CREATE);

        return new CreateAccountResponse(savedAccount.getId(), savedAccount.getOwnerName(), savedAccount.availabaleBalance(), savedAccount.getAccountAlias());
    }

}
