package com.magoya.challenge.infrastructure.out.persistence.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAccountRepository extends MongoRepository<AccountDocument, String> {
}
