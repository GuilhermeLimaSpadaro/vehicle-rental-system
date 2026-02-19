
package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;

import java.util.List;

public interface VehicleRepositoryInterface {

    void addVehicle(Vehicle vehicle) throws RepositoryException;

    void removeVehicle(String plate) throws RepositoryException;

    Vehicle findVehicleByPlate(String plate) throws RepositoryException;

    List<Vehicle> listVehicles();
}
