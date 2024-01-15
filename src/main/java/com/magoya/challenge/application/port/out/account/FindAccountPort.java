package com.magoya.challenge.application.port.out.account;

import com.magoya.challenge.domain.Account;

public interface FindAccountPort {
    Account findById(String accountId);
}
