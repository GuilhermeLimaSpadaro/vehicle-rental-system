package br.com.portifolio.vehiclerentalsys.repository.implementation;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.DataBaseException;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.ClientDao;

import java.sql.*;
import java.util.*;

public class ClientDaoJDBC implements ClientDao {
    private final Connection conn;

    public ClientDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertClient(Client client) {
        String sql = "INSERT INTO client " +
                "(id, name, document, phone) " +
                "VALUES (?, ?, ?, ?) ";
        try (PreparedStatement st = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, client.getId());
            st.setString(2, client.getName());
            st.setString(3, client.getDocument());
            st.setString(4, client.getPhone());
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    client.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public void removeClient(int id) {
        String sql = "DELETE FROM client " +
                "WHERE id = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Done! rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Client findClientById(int id) {
        String sql = "SELECT c.id, c.name, c.document, c.phone " +
                "FROM client c " +
                "WHERE c.id = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return instantiationClient(rs);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<Client> findAllClients() {
        String sql = "SELECT c.id, c.name, c.document, c.phone " +
                "FROM client c " +
                "ORDER BY id ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                Set<Client> clientSet = new HashSet<>();
                Map<Integer, Client> clientMap = new HashMap<>();
                while (rs.next()) {
                    Client client = clientMap.get(rs.getInt("id"));
                    if (client == null) {
                        client = instantiationClient(rs);
                        clientMap.put(rs.getInt("id"), client);
                    }
                    clientSet.add(client);
                }
                return clientSet;
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Client instantiationClient(ResultSet rs) {
        try {
            return new Client(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("document"),
                    rs.getString("phone"));
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
