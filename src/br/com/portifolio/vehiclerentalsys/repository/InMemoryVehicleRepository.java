package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;

import java.util.LinkedHashSet;
import java.util.Set;

public class InMemoryVehicleRepository implements VehicleRepositoryInterface {

    private final Set<Vehicle> vehicleList = new LinkedHashSet<>();

    @Override
    public void addVehicle(Vehicle vehicle) throws RepositoryException {
        if (this.vehicleList.contains(vehicle)) {
            throw new RepositoryException("Veiculo ja cadastrado!");
        }
        vehicleList.add(vehicle);
    }

    @Override
    public void removeVehicle(String plate) throws RepositoryException {
        Vehicle vehicle = this.findVehicleByPlate(plate);
        this.vehicleList.remove(vehicle);
    }

    @Override
    public Vehicle findVehicleByPlate(String plate) throws RepositoryException {
        vehicleList.stream()
                .filter(x -> x.getPlate().equals(plate))
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Veiculo nao encontrado! Placa: " + plate));
        throw new RepositoryException("Veiculo nao encontrado! Placa: " + plate);
    }

    @Override
    public Set<Vehicle> listVehicles() {
        return vehicleList;
    }
}
