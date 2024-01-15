package com.magoya.challenge.infrastructure.out.persistence.mongo;

import com.magoya.challenge.application.port.out.account.FindAccountPort;
import com.magoya.challenge.application.port.out.account.SaveAccountPort;
import com.magoya.challenge.application.port.out.account.UpdateAccountPort;
import com.magoya.challenge.common.PersistenceAdapter;
import com.magoya.challenge.domain.Account;
import com.magoya.challenge.infrastructure.out.persistence.mongo.mapper.MongoAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;

@PersistenceAdapter
@RequiredArgsConstructor
@Primary
public class AccountPersistenceMongoAdapter  implements FindAccountPort, UpdateAccountPort, SaveAccountPort {

    private final MongoAccountRepository accountRepository;
    private final MongoTransactionRepository transactionRepository;

    /**
     * Find an account based on account id
     * @param accountId to search on database
     * @return Account model
     */
    @Override
    public Account findById(String accountId) {
        return accountRepository
                .findById(String.valueOf(accountId))
                .map(MongoAccountMapper::documentToDomain)
                .orElseThrow();
    }

    /**
     * Save an account alone, without their transactions
     * @param account
     * @return
     */
    @Override
    public Account save(Account account) {
        AccountDocument savedAccount = accountRepository.save(MongoAccountMapper.domainToDocument(account));
        return MongoAccountMapper.documentToDomain(savedAccount);
    }

    /**
     * Simple update process to save all transactions and account if needed
     * @param account
     */
    @Override
    public void update(Account account) {
        AccountDocument document = MongoAccountMapper.domainToDocument(account);
        var savedTx = transactionRepository.saveAll(document.getTransactions());
        document.setTransactions(savedTx);
        accountRepository.save(document);
    }

}
