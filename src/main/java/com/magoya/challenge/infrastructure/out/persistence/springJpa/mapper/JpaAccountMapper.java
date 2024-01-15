package com.magoya.challenge.infrastructure.out.persistence.springJpa.mapper;

import com.magoya.challenge.domain.AccountAggregate;
import com.magoya.challenge.domain.OwnerAggregate;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.TransactionAggregate;
import com.magoya.challenge.infrastructure.out.persistence.mongo.TransactionDocument;
import com.magoya.challenge.infrastructure.out.persistence.springJpa.AccountEntity;
import com.magoya.challenge.infrastructure.out.persistence.springJpa.TransactionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JpaAccountMapper {

    /**
     * Simple basic mapper to convert from Domain model to DB model
     * @param entity to be converted
     * @return an {@link Account} with domain format
     */
    public static Account entityToDomain(AccountEntity entity) {
        Account account = new Account();
        account.setId(entity.getId().toString());

        AccountAggregate acc = new AccountAggregate(entity.getBalance(), entity.getAlias());
        OwnerAggregate owner = new OwnerAggregate(entity.getOwner());

        account.setAccount(acc);
        account.setOwner(owner);

        return account;
    }

    /**
     * Simple basic mapper to convert from DB model to Domain model
     * @param account to be converted
     * @return an {@link AccountEntity} with domain model format
     */
    public static AccountEntity domainToEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setId(UUID.fromString(account.getId()));
        entity.setOwner(account.getOwnerName());
        entity.setBalance(account.availabaleBalance());
        entity.setAlias(account.getAccountAlias());

        List<TransactionEntity> transactionEntityList = new ArrayList<>();

        for (TransactionAggregate tx: account.getTransactions()) {
            TransactionEntity txEntity = JpaTransacctionMapper.domainToEntity(tx);
            txEntity.setAccount(entity);
            transactionEntityList.add(txEntity);
        }

        entity.setTransactions(transactionEntityList);
        return entity;
    }
}
