package com.kata.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Table(name="WITHDRAWAL")
public class Withdrawal extends Operation {
    @Builder
    public Withdrawal(Date operationDate, double amount, Account account) {
        super(operationDate, amount, account);
    }
}

