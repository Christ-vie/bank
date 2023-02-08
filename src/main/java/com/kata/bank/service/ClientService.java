package com.kata.bank.service;

import com.kata.bank.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> findById(Long id);
    List<Client> findAll();
    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long clientId);
}
