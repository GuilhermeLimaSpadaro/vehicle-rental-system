package br.com.portifolio.vehiclerentalsys.repository.implementation;

import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.DataBaseException;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.VehicleDao;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VehicleDaoJDBC implements VehicleDao {
    private final Connection conn;

    public VehicleDaoJDBC(Connection conn) {
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