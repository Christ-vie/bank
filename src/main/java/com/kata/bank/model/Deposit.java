package com.kata.bank.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

public class Deposit extends Operation {
    @Builder
    public Deposit(Date operationDate, double amount, Account account) {
        super(operationDate, amount, account);
    }
}
