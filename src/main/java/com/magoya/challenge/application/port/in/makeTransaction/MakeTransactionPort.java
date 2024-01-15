package com.magoya.challenge.application.port.in.makeTransaction;

import com.magoya.challenge.application.port.out.account.BalanceResponse;

public interface MakeTransactionPort {
    BalanceResponse execute (MakeTransactionCommand command);
}
