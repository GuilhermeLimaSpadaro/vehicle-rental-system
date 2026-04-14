package br.com.portifolio.vehiclerentalsys.ui;

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
            System.out.print("ID: ");
            int id = ScannerUtils.intValidation(input);
            System.out.println("Data de saida registrada.");
            LocalDate startDate = LocalDate.now();
            System.out.print("Data de entrada: ");
            String inputDate = input.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate endDate = LocalDate.parse(inputDate, dtf);
            System.out.print("Informe o ID do cliente: ");
            int clientId = ScannerUtils.intValidation(input);
            System.out.println("Informe o id do veiculo: ");
            int vehicleId = ScannerUtils.intValidation(input);
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
        Set<Rental> rentals = rentalController.getAllRentals();
        if (rentals.isEmpty()) {
            System.out.println("Lista de alugueis vazia!");
        } else {
            for (Rental rental : rentals) {
                double totalPayment = rentalController.totalPayment(rental);
                System.out.println(rental);
                System.out.println("Valor total: " + totalPayment);
            }
        }
    }

    public void vehicleReturn(Scanner input) {
        try {
            System.out.print("Informe o ID do aluguel: ");
            int id = ScannerUtils.intValidation(input);
            Rental rental = rentalController.getRentalById(id);
            System.out.println("Sucesso ao registrar devolucao!");
            System.out.println("Total a ser pago: " + rentalController.totalPayment(rental));
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }
}