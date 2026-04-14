package br.com.portifolio.vehiclerentalsys.ui.console;

import br.com.portifolio.vehiclerentalsys.controller.RentalController;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.domain.entities.Rental;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

public class RentalUI {
    private final RentalController rentalController;

    public RentalUI(RentalController rentalController) {
        this.rentalController = rentalController;
    }

    public void createRental(Scanner input) {
        System.out.println();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.print("ID: ");
            int id = ScannerUtils.intValidation(input);
            System.out.print("Data de saida do veiculo: ");
            String checkInDate = input.nextLine();
            LocalDate startDate = LocalDate.parse(checkInDate, dtf);
            System.out.print("Data de entrada do veiculo: ");
            String checkOutDate = input.nextLine();
            LocalDate endDate = LocalDate.parse(checkOutDate, dtf);
            System.out.print("Informe o ID do cliente: ");
            int clientId = ScannerUtils.intValidation(input);
            System.out.print("Informe a placa do veiculo: ");
            String vehiclePlate = input.nextLine();
            rentalController.createRental(id, startDate, endDate, clientId, vehiclePlate);
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeRental(Scanner input) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            rentalController.removeRental(id);
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findRentalById(Scanner input) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            System.out.println(rentalController.getRentalById(id));
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listRental() {
        Set<Rental> rentalSet = rentalController.getAllRentals();
        rentalSet.forEach(System.out::println);
    }

    public double totalPayment(Scanner input) {
        System.out.println("Informe o id do contrato: ");
        int id = input.nextInt();
        Rental rental = rentalController.getRentalById(id);
        return rentalController.totalPayment(rental);
    }
}