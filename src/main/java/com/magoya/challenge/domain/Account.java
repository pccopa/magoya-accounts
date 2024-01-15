package com.magoya.challenge.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String id;
    private AccountAggregate account;
    private OwnerAggregate owner;
    private List<TransactionAggregate> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public boolean isBalanceGreaterThan (BigDecimal amount) {
        return account.isBalanceGreaterThan(amount);
    }

    public BigDecimal availabaleBalance () {
        return account.getBalance();
    }

    public String getAccountAlias() {
        return account.getAlias();
    }
    public BigDecimal deposit (BigDecimal amount) {
        account.deposit(amount);
        return account.getBalance();
    }

    public BigDecimal withdrawal (BigDecimal amount) {
        account.whitdrawal(amount);
        return account.getBalance();
    }

    public String getOwnerName () {
        return owner.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(AccountAggregate account) {
        this.account = account;
    }

    public void setOwner(OwnerAggregate owner) {
        this.owner = owner;
    }

    public void addTransaction (TransactionAggregate transaction) {
        if (transactions==null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
    }

    public List<TransactionAggregate> getTransactions () {
        return transactions;
    }

    public TransactionAggregate getLastTransaction () {
        return !transactions.isEmpty() ? transactions.get(transactions.size()-1) : null;
    }

}
