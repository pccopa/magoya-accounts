package com.magoya.challenge.application.port.out.account;

import com.magoya.challenge.domain.Account;

public interface SaveAccountPort {

    Account save (Account account);
}
