
package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.ClientException;

import java.util.Set;

public interface ClientRepositoryInterface {
    void add(Client client) throws ClientException;

    void removeClient(int id) throws ClientException;

    public Client findClientById(int id) throws ClientException;

    Set<Client> listClients() throws ClientException;
}
