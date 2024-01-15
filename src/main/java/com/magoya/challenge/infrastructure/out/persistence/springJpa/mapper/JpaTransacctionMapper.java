package com.magoya.challenge.infrastructure.out.persistence.springJpa.mapper;

import com.magoya.challenge.domain.TransactionAggregate;
import com.magoya.challenge.infrastructure.out.persistence.mongo.AccountDocument;
import com.magoya.challenge.infrastructure.out.persistence.springJpa.TransactionEntity;

public class JpaTransacctionMapper {

    /**
     * Simple basic mapper to convert from Domain model to DB model
     * @param transaction to be converted
     * @return an {@link TransactionEntity} with persistence model format
     */
    public static TransactionEntity domainToEntity(TransactionAggregate transaction){
        TransactionEntity entity = new TransactionEntity();
        entity.setAmount(transaction.getAmount());
        entity.setType(transaction.getType());
        return entity;
    }

    /**
     * Simple basic mapper to convert from DB model to Domain model
     * @param entity to be converted
     * @return an {@link TransactionAggregate} with domain model format
     */
    public static TransactionAggregate entityToDomain(TransactionEntity entity) {
        return new TransactionAggregate(entity.getAmount(), entity.getType());
    }


}
