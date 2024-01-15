package com.magoya.challenge.infrastructure.out.persistence.springJpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, String> {
}
