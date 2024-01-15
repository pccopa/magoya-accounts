package com.magoya.challenge.infrastructure.out.persistence.mongo.mapper;

import com.magoya.challenge.domain.Account;
import com.magoya.challenge.domain.TransactionAggregate;
import com.magoya.challenge.infrastructure.out.persistence.mongo.AccountDocument;
import com.magoya.challenge.infrastructure.out.persistence.mongo.TransactionDocument;

public class MongoTransactionMapper {

    /**
     * Simple basic mapper to convert from Domain model to DB model
     * @param transaction to be converted
     * @return an {@link TransactionDocument} with domain format
     */
    public static TransactionDocument domainToDocument(TransactionAggregate transaction){
        TransactionDocument document = new TransactionDocument();
        document.setAmount(transaction.getAmount());
        document.setType(transaction.getType());
        return document;
    }

    /**
     * Simple basic mapper to convert from DB model to Domain model
     * @param document to be converted
     * @return an {@link TransactionAggregate} with persistence model format
     */
    public static TransactionAggregate documentToDomain(TransactionDocument document) {
        return new TransactionAggregate(document.getAmount(), document.getType());
    }


}
