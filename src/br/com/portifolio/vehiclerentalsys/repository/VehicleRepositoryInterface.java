package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;

import java.util.Set;

public interface VehicleRepositoryInterface {

    void addVehicle(Vehicle vehicle) throws RepositoryException;

    void removeVehicle(String plate) throws RepositoryException;

    Vehicle findVehicleByPlate(String plate) throws RepositoryException;

    Set<Vehicle> listVehicles();
}
