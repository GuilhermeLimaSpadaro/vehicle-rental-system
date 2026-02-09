
package br.com.portifolio.vehiclerentalsys.model.interfaces;

import br.com.portifolio.vehiclerentalsys.model.entities.Client;
import br.com.portifolio.vehiclerentalsys.model.exception.ClientException;

import java.util.Set;

public interface ClientInterface {
    void add(Client var1) throws ClientException;

    void removeClient(String var1) throws ClientException;

    Client findClientByName(String var1) throws ClientException;

    Set<Client> listClients() throws ClientException;
}
