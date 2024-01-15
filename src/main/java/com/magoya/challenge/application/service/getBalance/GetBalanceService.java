package com.magoya.challenge.application.service.getBalance;

import com.magoya.challenge.application.port.in.getBalance.GetBalanceCommand;
import com.magoya.challenge.application.port.in.getBalance.GetBalancePort;
import com.magoya.challenge.application.port.out.account.FindAccountPort;
import com.magoya.challenge.application.port.out.account.BalanceResponse;
import com.magoya.challenge.common.UseCase;
import com.magoya.challenge.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetBalanceService implements GetBalancePort {

    private final FindAccountPort findAccountPort;

    @Override
    @Transactional(readOnly = true)
    public BalanceResponse getBalance(GetBalanceCommand command) {
        Account account = findAccountPort.findById(command.getAccountId());
        return new BalanceResponse(command.getAccountId(), account.availabaleBalance());
    }
}
