package com.magoya.challenge.infrastructure.in.web;

import com.magoya.challenge.application.port.in.makeTransaction.MakeTransactionCommand;
import com.magoya.challenge.application.port.in.makeTransaction.MakeTransactionPort;
import com.magoya.challenge.application.port.out.account.BalanceResponse;
import com.magoya.challenge.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping ("v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final MakeTransactionPort transactionPort;

    @PostMapping
    BalanceResponse transfer(@RequestBody MakeTransactionCommand request) {
        return transactionPort.execute(request);
    }

}
