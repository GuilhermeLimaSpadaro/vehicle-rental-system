package br.com.portifolio.vehiclerentalsys.controller;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.repository.VehicleRepositoryInterface;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.util.Scanner;
import java.util.Set;

public class VehicleController {

    public void addVehicle(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        System.out.println();

        try {
            System.out.print("ID: ");
            int id = ScannerUtils.intValidation(input);
            System.out.print("Modelo: ");
            String model = input.nextLine();
            System.out.print("Marca: ");
            String mark = input.nextLine();
            System.out.print("Placa: ");
            String plate = input.nextLine();
            System.out.print("Preco/Dia: ");
            double pricePerDay = ScannerUtils.doubleValidation(input);
            System.out.print("Categoria: ");
            Categories categories = ScannerUtils.categoriesEnum(input);
            System.out.print("Disponibilidade: ");
            Availability availability = ScannerUtils.availabilityEnum(input);
            Vehicle vehicle = new Vehicle(id, model, mark, plate, pricePerDay, categories, availability);
            vehicleRepo.addVehicle(vehicle);
        } catch (RepositoryException | DomainException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeVehicle(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        try {
            System.out.print("Informe a placa do carro que deseja remover: ");
            String plate = input.nextLine();

            vehicleRepo.removeVehicle(plate);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findVehicleByPlate(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        try {
            System.out.print("Informe a placa do veiculo que deseja: ");
            String plate = input.nextLine();
            Vehicle vehicle = vehicleRepo.findVehicleByPlate(plate);
            System.out.println(vehicle);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listVehicles(VehicleRepositoryInterface vehicleRepo) {
        Set<Vehicle> vehicles = vehicleRepo.listVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("Lista de veiculos vazia!");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }
}
