package br.com.portifolio.vehiclerentalsys.repository.implementation;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.ClientDao;

import java.sql.Connection;
import java.util.Set;

public class ClientDaoJDBC implements ClientDao {
    private final Connection conn;

    public ClientDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insertClient(Client client) {
    }

    @Override
    public void removeClient(int id) {
    }

    @Override
    public Client findClientById(int id) {
        return null;
    }

    @Override
    public Set<Client> findAllClients() {
        return Set.of();
    }
}
