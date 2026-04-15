package main.java.com.vehiclerental.controller;

import main.java.com.vehiclerental.domain.entities.Vehicle;
import main.java.com.vehiclerental.domain.enums.Availability;
import main.java.com.vehiclerental.domain.enums.Categories;
import main.java.com.vehiclerental.service.VehicleService;

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

    public Vehicle getVehicleByPlate(String plate) {
        return vehicleService.getVehicleByPlate(plate);
    }

    public Set<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}
