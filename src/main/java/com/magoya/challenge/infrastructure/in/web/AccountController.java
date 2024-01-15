package com.magoya.challenge.infrastructure.in.web;

import com.magoya.challenge.application.port.in.createAccount.CreateAccountCommand;
import com.magoya.challenge.application.port.in.createAccount.CreateAccountPort;
import com.magoya.challenge.application.port.in.getBalance.GetBalanceCommand;
import com.magoya.challenge.application.port.in.getBalance.GetBalancePort;
import com.magoya.challenge.application.port.out.account.CreateAccountResponse;
import com.magoya.challenge.application.port.out.account.BalanceResponse;
import com.magoya.challenge.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RequestMapping("v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final CreateAccountPort createAccountPort;
    private final GetBalancePort balancePort;


    @PostMapping
    public CreateAccountResponse transfer(@RequestBody CreateAccountCommand request) {
        return createAccountPort.create(request);
    }

    @GetMapping("{id}/balance")
    public BalanceResponse getBalance (@PathVariable String id) {
        GetBalanceCommand command = new GetBalanceCommand(id);
        return balancePort.getBalance(command);
    }

}
