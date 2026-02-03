package br.com.portfolio.vehiclerental.model.interfaces;

import br.com.portfolio.vehiclerental.model.entities.Vehicle;
import br.com.portfolio.vehiclerental.model.exceptions.VehicleException;

public interface VehicleInterface {
	
	public void addVehicle(Vehicle vehicle) throws VehicleException;
	
	public void removeVehicle(String model) throws VehicleException;

	public Vehicle findVehicleByName(String model) throws VehicleException;
	
	public String listVehicles() throws VehicleException;
}
