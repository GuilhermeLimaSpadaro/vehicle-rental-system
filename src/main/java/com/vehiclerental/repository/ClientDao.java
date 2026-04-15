package main.java.com.vehiclerental.repository;

import main.java.com.vehiclerental.domain.entities.Client;

import java.util.Set;

public interface ClientDao {
    void insertClient(Client client);

    void removeClient(int id);

    Client findClientById(int id);

    Set<Client> findAllClients();
}
