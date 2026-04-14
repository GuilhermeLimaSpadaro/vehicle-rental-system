package br.com.portifolio.vehiclerentalsys.service;

import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.exception.BusinessException;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.VehicleDao;

import java.util.Set;

public class VehicleService {
    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void createVehicle(Vehicle vehicle) {
        if (vehicleDao.findVehicleByPlate(vehicle.getPlate()) != null) {
            throw new BusinessException("Veiculo ja existe!");
        }
        vehicleDao.insertVehicle(vehicle);
    }

    public void removeVehicle(String plate) {
        if (vehicleDao.findVehicleByPlate(plate) == null) {
            throw new BusinessException("Veiculo nao existe!");
        }
        vehicleDao.removeVehicle(plate);
    }

    public Vehicle getVehicleByPlate(String plate) {
        Vehicle vehicle = vehicleDao.findVehicleByPlate(plate);
        if (vehicle == null){
            throw new BusinessException("Veiculo nao existe!");
        }
        return vehicle;
    }

    public Set<Vehicle> getAllVehicles(){
        return vehicleDao.findAllVehicles();
    }
}
