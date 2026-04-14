package br.com.portifolio.vehiclerentalsys.service;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.BusinessException;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.ClientDao;

import java.util.Set;

public class ClientService {
    private final ClientDao clientDao;

    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void createClient(Client client) {
        if (clientDao.findClientById(client.getId()) != null) {
            throw new BusinessException("Cliente ja existe!");
        }
        clientDao.insertClient(client);
    }

    public void removeClient(int id) {
        if (clientDao.findClientById(id) == null) {
            throw new BusinessException("Cliente nao existe!");
        }
        clientDao.removeClient(id);
    }

    public Client getClientById(int id) {
        Client client = clientDao.findClientById(id);
        if (client == null) {
            throw new BusinessException("Cliente nao existe!");
        }
        return client;
    }

    public Set<Client> getAllClients(){
        return clientDao.findAllClients();
    }
}
