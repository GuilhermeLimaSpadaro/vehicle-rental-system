package main.java.com.vehiclerental.infrastructure.factory;

import main.java.com.vehiclerental.repository.impl.ClientRepositoryJDBC;
import main.java.com.vehiclerental.repository.impl.RentalRepositoryJDBC;
import main.java.com.vehiclerental.repository.impl.VehicleRepositoryJDBC;
import main.java.com.vehiclerental.repository.ClientDao;
import main.java.com.vehiclerental.repository.RentalDao;
import main.java.com.vehiclerental.repository.VehicleDao;

import java.sql.Connection;

public class DaoFactory {
    public VehicleDao createVehicleConnection(Connection conn) {
        return new VehicleRepositoryJDBC(conn);
    }

    public ClientDao createClientConnection(Connection conn) {
        return new ClientRepositoryJDBC(conn);
    }

    public RentalDao createRentalConnection(Connection conn) {
        return new RentalRepositoryJDBC(conn);
    }
}
