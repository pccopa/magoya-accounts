package com.magoya.challenge.application.port.in.makeTransaction;

import com.magoya.challenge.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeTransactionCommand {
    private String accountId;
    private BigDecimal amount;
    private TransactionType txType;
}
