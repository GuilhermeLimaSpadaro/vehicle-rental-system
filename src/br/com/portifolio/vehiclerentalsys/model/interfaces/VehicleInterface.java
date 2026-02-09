
package br.com.portifolio.vehiclerentalsys.model.interfaces;

import br.com.portifolio.vehiclerentalsys.model.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.model.exception.VehicleException;

import java.util.List;

public interface VehicleInterface {
    void addVehicle(Vehicle var1) throws VehicleException;

    void removeVehicle(String var1) throws VehicleException;

    Vehicle findVehicleByName(String var1) throws VehicleException;

    List<Vehicle> listVehicles() throws VehicleException;
}
