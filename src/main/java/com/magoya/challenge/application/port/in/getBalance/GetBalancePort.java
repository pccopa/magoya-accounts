package com.magoya.challenge.application.port.in.getBalance;

import com.magoya.challenge.application.port.out.account.BalanceResponse;

public interface GetBalancePort {

    BalanceResponse getBalance (GetBalanceCommand command);

}
