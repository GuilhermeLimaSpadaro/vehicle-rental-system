package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;

import java.util.Set;
import java.util.TreeSet;

public class InMemoryClientRepository implements ClientRepositoryInterface {

    private final Set<Client> clientList = new TreeSet<>();

    @Override
    public void add(Client client) throws RepositoryException {
        if (this.clientList.contains(client)) {
            throw new RepositoryException("Cliente ja cadastrado.");
        }
        this.clientList.add(client);
    }

    @Override
    public void removeClient(int id) throws RepositoryException {
        Client find = this.findClientById(id);
        this.clientList.remove(find);
    }

    @Override
    public Client findClientById(int id) throws RepositoryException {
        clientList.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Cliente nao encontrado."));
        throw new RepositoryException("Cliente nao encontrado! ID: " + id);
    }

    @Override
    public Set<Client> listClients() {
        return clientList;
    }
}

