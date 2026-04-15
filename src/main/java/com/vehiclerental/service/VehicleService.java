package main.java.com.vehiclerental.service;

import main.java.com.vehiclerental.domain.entities.Vehicle;
import main.java.com.vehiclerental.domain.exception.DomainException;
import main.java.com.vehiclerental.repository.VehicleDao;

import java.util.Set;

public class VehicleService {
    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void createVehicle(Vehicle vehicle) {
        if (vehicleDao.findVehicleByPlate(vehicle.getPlate()) != null) {
            throw new DomainException("Veiculo ja existe!");
        }
        vehicleDao.insertVehicle(vehicle);
    }

    public void removeVehicle(String plate) {
        if (vehicleDao.findVehicleByPlate(plate) == null) {
            throw new DomainException("Veiculo nao existe!");
        }
        vehicleDao.removeVehicle(plate);
    }

    public Vehicle getVehicleByPlate(String plate) {
        Vehicle vehicle = vehicleDao.findVehicleByPlate(plate);
        if (vehicle == null){
            throw new DomainException("Veiculo nao existe!");
        }
        return vehicle;
    }

    public Set<Vehicle> getAllVehicles(){
        return vehicleDao.findAllVehicles();
    }
}
