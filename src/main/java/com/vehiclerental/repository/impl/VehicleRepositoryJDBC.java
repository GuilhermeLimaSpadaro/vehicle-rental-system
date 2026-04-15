package main.java.com.vehiclerental.repository.impl;

import main.java.com.vehiclerental.domain.entities.Vehicle;
import main.java.com.vehiclerental.domain.enums.Availability;
import main.java.com.vehiclerental.domain.enums.Categories;
import main.java.com.vehiclerental.infrastructure.exception.DataBaseException;
import main.java.com.vehiclerental.repository.VehicleDao;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class VehicleRepositoryJDBC implements VehicleDao {
    private final Connection conn;

    public VehicleRepositoryJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicle " +
                "(id, model, mark, plate, price_per_day, category, availability ) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, vehicle.getModel());
            st.setString(2, vehicle.getMark());
            st.setString(3, vehicle.getPlate());
            st.setDouble(4, vehicle.getPricePerDay());
            st.setObject(5, vehicle.getCategories().name());
            st.setString(6, vehicle.getAvailability().name());
            st.executeUpdate();
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    vehicle.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public void removeVehicle(String plate) {
        String sql = "DELETE FROM vehicle v " +
                "WHERE v.plate = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, plate);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Done! rows: " + rowsAffected);
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Vehicle findVehicleByPlate(String plate) {
        String sql = "SELECT v.id, v.model, v.mark, v.plate, v.price_per_day, v.category, v.availability FROM vehicle v " +
                "WHERE v.plate = ? ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, plate);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Categories categories = instatiationCategories(rs);
                    Availability availability = instatiationAvailability(rs);
                    return instatiationVehicle(rs, categories, availability);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<Vehicle> findAllVehicles() {
        String sql = "SELECT v.id, v.model, v.mark, v.plate, v.price_per_day, v.category, v.availability " +
                "FROM vehicle v " +
                "ORDER BY id ";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {
                Set<Vehicle> setVehicle = new HashSet<>();
                while (rs.next()) {
                    Vehicle vehicle = instatiationVehicle(rs, instatiationCategories(rs), instatiationAvailability(rs));
                    setVehicle.add(vehicle);
                }
                return setVehicle;
            }
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Categories instatiationCategories(ResultSet rs) {
        try {
            String category = rs.getString("category");
            return Categories.valueOf(category.toUpperCase());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Availability instatiationAvailability(ResultSet rs) {
        try {
            String availability = rs.getString("availability");
            return Availability.valueOf(availability.toUpperCase());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Vehicle instatiationVehicle(ResultSet rs, Categories categories, Availability availability) {
        try {
            return new Vehicle(
                    rs.getInt("id"),
                    rs.getString("model"),
                    rs.getString("mark"),
                    rs.getString("plate"),
                    rs.getDouble("price_per_day"),
                    categories,
                    availability);
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
    }
}