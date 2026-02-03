package br.com.portfolio.vehiclerental.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.portfolio.vehiclerental.model.entities.Vehicle;
import br.com.portfolio.vehiclerental.model.exceptions.VehicleException;
import br.com.portfolio.vehiclerental.model.interfaces.VehicleInterface;

public class VehicleRepository implements VehicleInterface {

	private List<Vehicle> vehicleList = new ArrayList<>();

	public VehicleRepository() {
	}

	public VehicleRepository(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	/**   **/
	
	@Override
	public void addVehicle(Vehicle vehicle) throws VehicleException {
		if (vehicleList.contains(vehicle)) {
			throw new VehicleException("Veiculo ja cadastrado!");
		}
		vehicleList.add(vehicle);
	}

	@Override
	public void removeVehicle(String model) throws VehicleException {
		Vehicle vehicle = findVehicleByName(model);
		vehicleList.remove(vehicle);
	}

	@Override
	public Vehicle findVehicleByName(String model) throws VehicleException {
		if (vehicleList.isEmpty()) {
			throw new VehicleException("Lista nao pode ser vazia!");
		}
		for (Vehicle vehicleObj : vehicleList) {
			if (model.trim().equalsIgnoreCase(vehicleObj.getModel())) {
				return vehicleObj;
			}
		}
			throw new VehicleException("Veiculo nao encontrado! ID: " + model);
	}
	
	@Override
	public String listVehicles() throws VehicleException {
		if (listVehicles().isEmpty()) {
			throw new VehicleException("Lista nao pode estar vazia!");
		}
		StringBuilder sb = new StringBuilder();
		for (Vehicle vehicle : vehicleList) {
			sb.append(vehicle.toString()).append("\n");
		}
		return sb.toString();
	}
}
