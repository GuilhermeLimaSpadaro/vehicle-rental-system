package main.java.com.vehiclerental.repository;

import main.java.com.vehiclerental.domain.entities.Vehicle;

import java.util.Set;

public interface VehicleDao {
    void insertVehicle(Vehicle vehicle);

    void removeVehicle(String plate);

    Vehicle findVehicleByPlate(String plate);

    Set<Vehicle> findAllVehicles();
}
