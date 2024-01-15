package com.magoya.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TransactionAggregate {

    private BigDecimal amount;
    private TransactionType type;

}
