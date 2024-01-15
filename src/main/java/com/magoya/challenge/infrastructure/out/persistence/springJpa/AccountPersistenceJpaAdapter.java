package com.magoya.challenge.infrastructure.out.persistence.springJpa;

import com.magoya.challenge.application.port.out.account.FindAccountPort;
import com.magoya.challenge.application.port.out.account.SaveAccountPort;
import com.magoya.challenge.application.port.out.account.UpdateAccountPort;
import com.magoya.challenge.common.PersistenceAdapter;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.infrastructure.out.persistence.springJpa.mapper.JpaAccountMapper;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AccountPersistenceJpaAdapter implements FindAccountPort, UpdateAccountPort, SaveAccountPort {

    private final JpaAccountRepository accountRepository;

    /**
     * Find an account using account id
     * @param accountId to search on DB
     * @return {@link Account} with domain model
     */
    @Override
    public Account findById(String accountId) {
        return accountRepository
                .findById(accountId)
                .map(JpaAccountMapper::entityToDomain)
                .orElseThrow();
    }


    /**
     * Updates an account on database
     * @param account with all data to save on cascade
     */
    @Override
    public void update(Account account) {
        accountRepository.save(JpaAccountMapper.domainToEntity(account));
    }

    /**
     * Save an account on database
     * @param account with all data to save on cascade
     */
    @Override
    public Account save(Account account) {
        AccountEntity savedAccount = accountRepository.save(JpaAccountMapper.domainToEntity(account));
        return JpaAccountMapper.entityToDomain(savedAccount);
    }
}
