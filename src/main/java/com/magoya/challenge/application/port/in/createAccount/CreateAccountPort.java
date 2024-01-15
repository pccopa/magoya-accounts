package com.magoya.challenge.application.port.in.createAccount;

import com.magoya.challenge.application.port.out.account.CreateAccountResponse;

public interface CreateAccountPort {
    CreateAccountResponse create (CreateAccountCommand command);

}
