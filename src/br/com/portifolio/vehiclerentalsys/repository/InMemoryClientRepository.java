package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.ClientException;

import java.util.Set;
import java.util.TreeSet;

public class InMemoryClientRepository implements ClientRepositoryInterface {
    private Set<Client> clientList = new TreeSet<>();

    public InMemoryClientRepository() {
    }

    public InMemoryClientRepository(Set<Client> clientList) {
        this.clientList = clientList;
    }

    public Set<Client> getClientList() {
        return this.clientList;
    }

    @Override
    public void add(Client client) throws ClientException {
        if (this.clientList.contains(client)) {
            throw new ClientException("Cliente ja cadastrado.");
        }
        this.clientList.add(client);
    }

    @Override
    public void removeClient(int id) throws ClientException {
        Client find = this.findClientById(id);
        this.clientList.remove(find);
    }

    @Override
    public Client findClientById(int id) throws ClientException {
        for (Client clientObj : this.clientList) {
            if (clientObj.getId().equals(id)) {
                return clientObj;
            }

        }
        throw new ClientException("Cadastro nao encontrado! ID: " + id);
    }

    @Override
    public Set<Client> listClients(){
        return clientList;
    }
}

