package main.java.com.vehiclerental.controller;

import main.java.com.vehiclerental.domain.entities.Client;
import main.java.com.vehiclerental.service.ClientService;

import java.util.Set;

public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public void createClient(int id, String name, String document, String phone) {
        Client client = new Client(id, name, document, phone);
        clientService.createClient(client);
    }

    public void removeClient(int id) {
        clientService.removeClient(id);
    }

    public Client getClientById(int id) {
        return clientService.getClientById(id);
    }

    public Set<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
