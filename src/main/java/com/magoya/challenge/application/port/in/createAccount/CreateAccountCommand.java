package com.magoya.challenge.application.port.in.createAccount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountCommand {

    private String ownerName;
    private String aliasAccount;

}
