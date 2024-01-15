package com.magoya.challenge.infrastructure.out.persistence.springJpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    private UUID id;
    private BigDecimal balance;
    private String alias;
    private String owner;
    @OneToMany (mappedBy = "account", cascade = {CascadeType.ALL})
    private List<TransactionEntity> transactions;
}
