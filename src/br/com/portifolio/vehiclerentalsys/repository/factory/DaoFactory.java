package br.com.portifolio.vehiclerentalsys.repository.factory;

import br.com.portifolio.vehiclerentalsys.repository.implementation.ClientDaoJDBC;
import br.com.portifolio.vehiclerentalsys.repository.implementation.RentalDaoJDBC;
import br.com.portifolio.vehiclerentalsys.repository.implementation.VehicleDaoJDBC;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.ClientDao;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.RentalDao;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.VehicleDao;

import java.sql.Connection;

public class DaoFactory {
    public VehicleDao createVehicleConnection(Connection conn) {
        return new VehicleDaoJDBC(conn);
    }

    public ClientDao createClientConnection(Connection conn) {
        return new ClientDaoJDBC(conn);
    }

    public RentalDao createRentalConnection(Connection conn) {
        return new RentalDaoJDBC(conn);
    }
}
