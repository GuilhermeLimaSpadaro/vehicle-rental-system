
package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.exception.VehicleException;

import java.util.List;

public interface VehicleRepositoryInterface {

    void addVehicle(Vehicle vehicle) throws VehicleException;

    void removeVehicle(String plate) throws VehicleException;

    Vehicle findVehicleByPlate(String plate) throws VehicleException;

    List<Vehicle> listVehicles();
}
