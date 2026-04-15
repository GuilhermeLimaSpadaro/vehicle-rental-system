package main.java.com.vehiclerental.service;

import main.java.com.vehiclerental.domain.entities.Client;
import main.java.com.vehiclerental.domain.exception.DomainException;
import main.java.com.vehiclerental.repository.ClientDao;

import java.util.Set;

public class ClientService {
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void createClient(Client client) {
        if (clientDao.findClientById(client.getId()) != null) {
            throw new DomainException("Cliente ja existe!");
        }
        clientDao.insertClient(client);
    }

    public void removeClient(int id) {
        if (clientDao.findClientById(id) == null) {
            throw new DomainException("Cliente nao existe!");
        }
        clientDao.removeClient(id);
    }

    public Client getClientById(int id) {
        Client client = clientDao.findClientById(id);
        if (client == null) {
            throw new DomainException("Cliente nao existe!");
        }
        return client;
    }

    public Set<Client> getAllClients(){
        return clientDao.findAllClients();
    }
}
