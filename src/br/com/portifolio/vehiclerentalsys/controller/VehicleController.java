package br.com.portifolio.vehiclerentalsys.controller;

import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.service.VehicleService;

import java.util.Set;

public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void createVehicle(int id, String model, String mark, String plate, double pricePerDay, Categories category, Availability availability) {
        Vehicle vehicle = new Vehicle(id, model, mark, plate, pricePerDay, category, availability);
        vehicleService.createVehicle(vehicle);
    }

    public void removeVehicle(String plate) {
        vehicleService.removeVehicle(plate);
    }

    public Vehicle getVehicleByPlate(String plate){
        return vehicleService.getVehicleByPlate(plate);
    }

    public Set<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
}
