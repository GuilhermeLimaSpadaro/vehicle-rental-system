package br.com.portifolio.vehiclerentalsys.repository.implementation;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.domain.entities.Rental;
import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.DataBaseException;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.RentalDao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RentalDaoJDBC implements RentalDao {
    private final Connection conn;

    public RentalDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertRental(Rental rental) {
        String sql = "INSERT INTO rental " +
                "(id, start_date, end_date, client_id, vehicle_id) " +
                "VALUES (?, ?, ?, ?, ?) ";

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, rental.getId());
            st.setDate(2, Date.valueOf(rental.getStartDate()));
            st.setDate(3, Date.valueOf(rental.getEndDate()));
            st.setInt(4, rental.getClient().getId());
            st.setInt(5, rental.getVehicle().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        rental.setId(id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override

    public Rental findRentalById(int id) {
        String sql = "SELECT r.id AS rental_id, r.start_date, r.end_date, " +
                "v.id AS vehicle_id, v.model, v.mark, v.plate, v.price_per_day, v.category, v.availability, " +
                "c.id AS client_id, c.name, c.document, c.phone " +
                " FROM rental r " +
                "INNER JOIN vehicle v ON r.vehicle_id = v.id " +
                "INNER JOIN client c ON r.client_id = c.id " +
                "WHERE r.id = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Client client = instantiationClient(rs);
                    Vehicle vehicle = instantiationVehicle(rs);
                    return instantiationRental(rs, client, vehicle);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeRental(int id) {
        String sql = "DELETE FROM rental " +
                "WHERE id = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Done! Rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Set<Rental> findAllRental() {
        String sql = "SELECT r.id AS rental_id, r.start_date, r.end_date, " +
                "v.id AS vehicle_id, v.model, v.mark, v.plate, v.price_per_day, v.category, v.availability, " +
                "c.id AS client_id, c.name, c.document, c.phone " +
                "FROM rental r " +
                "INNER JOIN vehicle v ON r.vehicle_id = v.id " +
                "INNER JOIN client c ON r.client_id = c.id " +
                " ORDER BY r.id ";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            Set<Rental> rentalSet = new TreeSet<>();
            try (ResultSet rs = st.executeQuery()) {
                Map<Integer, Client> clientMap = new HashMap<>();
                Map<Integer, Vehicle> vehicleMap = new HashMap<>();
                Map<Integer, Rental> rentalMap = new HashMap<>();
                while (rs.next()) {
                    Client client = clientMap.get(rs.getInt("client_id"));
                    Vehicle vehicle = vehicleMap.get(rs.getInt("vehicle_id"));
                    Rental rental = rentalMap.get(rs.getInt("rental_id"));
                    if (client == null) {
                        client = instantiationClient(rs);
                        clientMap.put(rs.getInt("client_id"), client);
                    }
                    if (vehicle == null) {
                        vehicle = instantiationVehicle(rs);
                        vehicleMap.put(rs.getInt("vehicle_id"), vehicle);
                    }
                    if (rental == null) {
                        rental = instantiationRental(rs, client, vehicle);
                        rentalMap.put(rs.getInt("rental_id"), rental);
                    }
                    rentalSet.add(rental);
                }
                return rentalSet;
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Categories instantiationCategories(ResultSet rs) {
        try {
            String category = rs.getString("category");
            return Categories.valueOf(category.toUpperCase());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Availability instantiationAvailability(ResultSet rs) {
        try {
            String availability = rs.getString("availability");
            return Availability.valueOf(availability.toUpperCase());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Vehicle instantiationVehicle(ResultSet rs) {
        try {
            Categories categories = instantiationCategories(rs);
            Availability availability = instantiationAvailability(rs);
            return new Vehicle(
                    rs.getInt("vehicle_id"),
                    rs.getString("model"),
                    rs.getString("mark"),
                    rs.getString("plate"),
                    rs.getDouble("price_per_day"),
                    categories,
                    availability
            );
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Client instantiationClient(ResultSet rs) {
        try {
            return new Client(
                    rs.getInt("client_id"),
                    rs.getString("name"),
                    rs.getString("document"),
                    rs.getString("phone")
            );
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage()
            );
        }
    }

    private Rental instantiationRental(ResultSet rs, Client client, Vehicle vehicle) {
        try {

            return new Rental(
                    rs.getInt("rental_id"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    client,
                    vehicle
            );
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}
