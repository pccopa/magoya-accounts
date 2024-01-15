package com.magoya.challenge.infrastructure.out.persistence.mongo.mapper;

import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.AccountAggregate;
import com.magoya.challenge.domain.OwnerAggregate;
import com.magoya.challenge.domain.TransactionAggregate;
import com.magoya.challenge.infrastructure.out.persistence.mongo.AccountDocument;
import com.magoya.challenge.infrastructure.out.persistence.mongo.TransactionDocument;
import com.magoya.challenge.infrastructure.out.persistence.springJpa.TransactionEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MongoAccountMapper {


    /**
     * Simple basic mapper to convert from DB model to Domain model
     * @param document to be converted
     * @return an {@link Account} with domain format
     */
    public static Account documentToDomain(AccountDocument document) {

        Account account = new Account();
        account.setId(document.getId());

        AccountAggregate acc = new AccountAggregate(document.getBalance(), document.getAlias());
        OwnerAggregate owner = new OwnerAggregate(document.getName());

        account.setAccount(acc);
        account.setOwner(owner);

        return account;
    }

    /**
     * Simple basic mapper to convert from Domain model to DB model
     * @param account to be converted
     * @return an {@link AccountDocument} with persistence model format
     */
    public static AccountDocument domainToDocument(Account account) {

        AccountDocument document = new AccountDocument();
        document.setId(account.getId());
        document.setName(account.getOwnerName());
        document.setBalance(account.availabaleBalance());
        document.setAlias(account.getAccountAlias());

        List<TransactionDocument> transactions = new ArrayList<>();

        for (TransactionAggregate tx: account.getTransactions()) {
            TransactionDocument txDocument = MongoTransactionMapper.domainToDocument(tx);
            txDocument.setAccount(document);
            transactions.add(txDocument);
        }

        document.setTransactions(transactions);
        return document;
    }
}
