package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.model.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.model.exception.VehicleException;
import br.com.portifolio.vehiclerentalsys.model.interfaces.VehicleInterface;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements VehicleInterface {
    private List<Vehicle> vehicleList = new ArrayList<>();

    public VehicleRepository() {
    }

    public VehicleRepository(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Vehicle> getVehicleList() {
        return this.vehicleList;
    }

    public void addVehicle(Vehicle vehicle) throws VehicleException {
        if (this.vehicleList.contains(vehicle)) {
            throw new VehicleException("Veiculo ja cadastrado!");
        }
        vehicleList.add(vehicle);
    }

    public void removeVehicle(String model) throws VehicleException {
        Vehicle vehicle = this.findVehicleByName(model);
        this.vehicleList.remove(vehicle);
    }

    public Vehicle findVehicleByName(String model) throws VehicleException {
        if (this.vehicleList.isEmpty()) {
            throw new VehicleException("Lista nao pode ser vazia!");
        } else {
            for (Vehicle vehicleObj : this.vehicleList) {
                if (model.trim().equalsIgnoreCase(vehicleObj.getModel())) {
                    return vehicleObj;
                }
            }

            throw new VehicleException("Veiculo nao encontrado! ID: " + model);
        }
    }

    public List<Vehicle> listVehicles() throws VehicleException {
        if (this.vehicleList.isEmpty()) {
            throw new VehicleException("Lista nao pode estar vazia!");
        }
        return vehicleList;
    }
}
