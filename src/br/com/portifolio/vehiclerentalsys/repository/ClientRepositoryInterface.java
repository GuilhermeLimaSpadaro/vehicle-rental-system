
package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;

import java.util.Set;

public interface ClientRepositoryInterface {

    void add(Client client) throws RepositoryException;

    void removeClient(int id) throws RepositoryException;

    Client findClientById(int id) throws RepositoryException;

    Set<Client> listClients();
}
