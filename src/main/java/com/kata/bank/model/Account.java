package com.kata.bank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "Account")
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "first_name")
    private String accountId;
    @Column (name = "date")
    private Date date;
    @Column(name = "Amount")
    private Double amount;
    @Column(name = "Balance")
    private Double balance;
    @Column(name = "DISCOVERED")
    private double discovered;
    @ManyToOne
    @JoinColumn(name = "CLIENT")
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<Operation> operations;


}
