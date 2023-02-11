package com.kata.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name="Client")
public class Client implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="CLIENT_ID", nullable = false)
    private Long clientId;

    @Column(name="NAME")
    private String name;
    @Column(name="LAST_UPDATE")
    private Date lastUpdate;
    @Column(name="EMAIL")
    private String email;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Account> accounts;

}