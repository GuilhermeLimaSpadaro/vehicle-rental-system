package main.java.com.vehiclerental.view;

import main.java.com.vehiclerental.controller.VehicleController;
import main.java.com.vehiclerental.domain.entities.Vehicle;
import main.java.com.vehiclerental.domain.enums.Availability;
import main.java.com.vehiclerental.domain.enums.Categories;
import main.java.com.vehiclerental.infrastructure.exception.DataBaseException;
import main.java.com.vehiclerental.domain.exception.DomainException;
import main.java.com.vehiclerental.utils.ScannerUtils;

import java.util.Scanner;
import java.util.Set;

public class VehicleUI {
    private final VehicleController vehicleController;

    public VehicleUI(VehicleController vehicleController) {
        this.vehicleController = vehicleController;
    }

    public void createVehicle(Scanner input) {
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
            vehicleController.createVehicle(id, model, mark, plate, pricePerDay, categories, availability);
        } catch (DataBaseException | DomainException e) {
            System.out.println(e.getMessage());
        }

    }

    public void removeVehicle(Scanner input) {
        try {
            System.out.print("Informe a placa do carro que deseja remover: ");
            String plate = input.nextLine();
            vehicleController.removeVehicle(plate);
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findVehicleByPlate(Scanner input) {
        try {
            System.out.print("Informe a placa do veiculo que deseja: ");
            String plate = input.nextLine();
            System.out.println(vehicleController.getVehicleByPlate(plate));
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listVehicles() {
        Set<Vehicle> list = vehicleController.getAllVehicles();
        list.forEach(System.out::println);
    }
}
