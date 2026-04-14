package br.com.portifolio.vehiclerentalsys.repository.interfaces;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;

import java.util.Set;

public interface ClientDao {
    void insertClient(Client client);

    void removeClient(int id);

    Client findClientById(int id);

    Set<Client> findAllClients();
}
