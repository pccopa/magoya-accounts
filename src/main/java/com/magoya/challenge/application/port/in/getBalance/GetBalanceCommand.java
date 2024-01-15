package com.magoya.challenge.application.port.in.getBalance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBalanceCommand {

    private String accountId;

}
