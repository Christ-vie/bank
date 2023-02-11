package com.kata.bank.service.impl;

import com.kata.bank.model.Account;
import com.kata.bank.model.Deposit;
import com.kata.bank.model.Operation;
import com.kata.bank.model.Withdrawal;
import com.kata.bank.repositories.AccountRepository;
import com.kata.bank.repositories.OperationRepository;
import com.kata.bank.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    @Autowired
    private final OperationRepository operationRepository;
    @Autowired
    private final AccountRepository accountRepository;

    
    public Optional<Operation> findById(long id) {
        return operationRepository.findById(id);
    }


    @Override
    public List<Operation> findAll() {
        return operationRepository.findAll();
    }

    @Override
    public List<Operation> findAllOperationsByAccount(String accountId) {
        return operationRepository.findAllByAccountId(accountId);
    }

    @Override
    public void deposit(String accountId, double amount) {
        Account currentAccount = getAccount(accountId);
        Deposit deposit = Deposit.builder().operationDate(new Date()).amount(amount).account(currentAccount).build();
        saveOperation(deposit);
        currentAccount.setBalance(currentAccount.getBalance() + amount);
        accountRepository.save(currentAccount);
    }

    @Override
    public void withdrawal(String accountId, double amount) {
        Account currentAccount = getAccount(accountId);
        if (currentAccount.getBalance() + currentAccount.getDiscovered() < amount)
            throw new RuntimeException("insufficient balance");
        Withdrawal withdrawal = Withdrawal.builder().operationDate(new Date()).amount(amount).account(currentAccount).build();
        saveOperation(withdrawal);
        currentAccount.setBalance(currentAccount.getBalance() - amount);
        accountRepository.save(currentAccount);
    }

    @Override
    public void payment(String accountTransmitter, String accountBeneficiary, double amount) {
        if (accountTransmitter.equals(accountBeneficiary))
            throw new RuntimeException("impossible to perform the operation");
        withdrawal(accountTransmitter, amount);
        deposit(accountBeneficiary, amount);
    }

    @Override
    public Operation saveOperation(Operation operation) {
       return operationRepository.save(operation);
    }

    public Account getAccount(String accountId) {
        if (accountRepository.findById(accountId).isPresent())
            throw new RuntimeException("Account not found");
        return accountRepository.findById(accountId).get();
    }

}
