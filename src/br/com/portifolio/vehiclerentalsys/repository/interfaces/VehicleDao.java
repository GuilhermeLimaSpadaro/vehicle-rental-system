package br.com.portifolio.vehiclerentalsys.repository.interfaces;

import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;

import java.util.Set;

public interface VehicleDao {
    void insertVehicle(Vehicle vehicle);

    void removeVehicle(String plate);

    Vehicle findVehicleByPlate(String plate);

    Set<Vehicle> findAllVehicles();
}
