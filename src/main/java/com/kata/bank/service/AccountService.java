package com.kata.bank.service;

import com.kata.bank.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> findById(String id);
    Account createAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(String accountId);

    List<Account> findAll();
    List<Account> findAccountsByClient(long clientId);


}
