package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.exception.VehicleException;

import java.util.ArrayList;
import java.util.List;

public class InMemoryVehicleRepository implements VehicleRepositoryInterface {
    private List<Vehicle> vehicleList = new ArrayList<>();

    public InMemoryVehicleRepository() {
    }

    public InMemoryVehicleRepository(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public void addVehicle(Vehicle vehicle) throws VehicleException {
        if (this.vehicleList.contains(vehicle)) {
            throw new VehicleException("Veiculo ja cadastrado!");
        }
        vehicleList.add(vehicle);
    }

    @Override
    public void removeVehicle(String plate) throws VehicleException {
        Vehicle vehicle = this.findVehicleByPlate(plate);
        this.vehicleList.remove(vehicle);
    }

    @Override
    public Vehicle findVehicleByPlate(String plate) throws VehicleException {
        for (Vehicle vehicleObj : this.vehicleList) {
            if (plate.trim().equalsIgnoreCase(vehicleObj.getPlate())) {
                return vehicleObj;
            }

        }
        throw new VehicleException("Veiculo nao encontrado! Placa: " + plate);
    }

    @Override
    public List<Vehicle> listVehicles() {
        return vehicleList;
    }
}
