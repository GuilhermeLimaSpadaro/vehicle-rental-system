package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.model.entities.Client;
import br.com.portifolio.vehiclerentalsys.model.exception.ClientException;
import br.com.portifolio.vehiclerentalsys.model.interfaces.ClientInterface;

import java.util.Set;
import java.util.TreeSet;

public class ClientRepository implements ClientInterface {
    private Set<Client> clientList = new TreeSet<>();

    public ClientRepository() {
    }

    public ClientRepository(Set<Client> clientList) {
        this.clientList = clientList;
    }

    public Set<Client> getClientList() {
        return this.clientList;
    }

    public void add(Client client) throws ClientException {
        if (this.clientList.contains(client)) {
            throw new ClientException("Cliente ja cadastrado.");
        }
        this.clientList.add(client);
    }

    public void removeClient(String name) throws ClientException {
        Client find = this.findClientByName(name);
        this.clientList.remove(find);
    }

    public Client findClientByName(String name) throws ClientException {
        if (this.clientList.isEmpty()) {
            throw new ClientException("Lista nao pode ser vazia!");
        } else {
            for (Client clientObj : this.clientList) {
                if (name.trim().equalsIgnoreCase(clientObj.getName())) {
                    return clientObj;
                }
            }

            throw new ClientException("Cadastro nao encontrado: " + name);
        }
    }

    public Set<Client> listClients() throws ClientException {
        if (this.clientList.isEmpty()) {
            throw new ClientException("Lista nao pode ser vazia!");
        }
        return clientList;
    }
}

