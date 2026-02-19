package br.com.portifolio.vehiclerentalsys.controller;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.repository.VehicleRepositoryInterface;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.util.List;
import java.util.Scanner;

public class VehicleController {

    public void addVehicle(Scanner input, VehicleRepositoryInterface vehicleRepo) throws DomainException {
        System.out.println();
        System.out.print("ID: ");
        int id = ScannerUtils.intValidation(input);
        System.out.print("Modelo: ");
        String model = input.nextLine();
        System.out.print("Marca: ");
        String mark = input.nextLine();
        System.out.print("Placa: ");
        String plate = input.nextLine();
        System.out.print("Preco/Dia: ");
        double price = ScannerUtils.doubleValidation(input);
        System.out.print("Categoria: ");
        Categories categories = ScannerUtils.categoriesEnum(input);
        System.out.println("Disponibilidade: ");
        Availability availability = ScannerUtils.availabilityEnum(input);
        Vehicle vehicle = new Vehicle(id, model, mark, plate, price, categories, availability);
        try {
            vehicleRepo.addVehicle(vehicle);
        } catch (RepositoryException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeVehicle(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        System.out.print("Informe o modelo do carro que deseja remover: ");
        String model = input.nextLine();
        try {
            vehicleRepo.removeVehicle(model);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findVehicleByPlate(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        System.out.println("Informe a placa do veiculo que deseja: ");
        String plate = input.nextLine();
        try {
            Vehicle vehicle = vehicleRepo.findVehicleByPlate(plate);
            System.out.println(vehicle);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listVehicles(VehicleRepositoryInterface vehicleRepo) {
        List<Vehicle> vehicles = vehicleRepo.listVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("Lista de veiculos vazia!");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
}
