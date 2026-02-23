package br.com.portifolio.vehiclerentalsys.controller;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.repository.ClientRepositoryInterface;
import br.com.portifolio.vehiclerentalsys.repository.RentalRepositoryInterface;
import br.com.portifolio.vehiclerentalsys.repository.VehicleRepositoryInterface;
import br.com.portifolio.vehiclerentalsys.service.RentalService;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;

public class RentalController {

    public void addRental(Scanner input, ClientRepositoryInterface clientRepo, VehicleRepositoryInterface vehicleRepo, RentalRepositoryInterface rentalRepo) {
        System.out.println();
        try {
            System.out.print("ID: ");
            int rentalId = ScannerUtils.intValidation(input);
            System.out.println("Data de saida registrada.");
            LocalDate departureDate = LocalDate.now();
            System.out.print("Data de entrada: ");
            String dateOfEntry = input.nextLine();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate entryDate = LocalDate.parse(dateOfEntry, dtf);
            System.out.print("Informe o ID do cliente: ");
            int id = ScannerUtils.intValidation(input);
            Client client = clientRepo.findClientById(id);
            System.out.print("Informe a placa do veiculo: ");
            String plate = input.nextLine();
            Vehicle vehicle = vehicleRepo.findVehicleByPlate(plate);
            Rental rental = new Rental(rentalId, departureDate, entryDate, client, vehicle);
            rentalRepo.addRental(rental);
            vehicle.setAvailability(Availability.ALUGADO);
        } catch (RepositoryException | DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeRental(Scanner input, RentalRepositoryInterface rentalRepo) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            rentalRepo.removeRental(id);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findRentalById(Scanner input, RentalRepositoryInterface rentalRepo) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            Rental rental = rentalRepo.findRentalById(id);
            System.out.println(rental);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listRental(RentalRepositoryInterface rentalRepo, RentalService rentalService) {
        Set<Rental> rentals = rentalRepo.listContractsRental();
        if (rentals.isEmpty()) {
            System.out.println("Lista de alugueis vazia!");
        } else {
            for (Rental rental : rentals) {
                double totalPayment = rentalService.totalPayment(rental);
                System.out.println(rental);
                System.out.println("Valor total: " + totalPayment);
            }
        }
    }

    public void vehicleReturn(Scanner input, RentalService rentalService, RentalRepositoryInterface rentalRepo) {
        try {
            System.out.print("Informe o ID do aluguel: ");
            int id = ScannerUtils.intValidation(input);
            Rental rental = rentalRepo.findRentalById(id);
            rental.getVehicle().setAvailability(Availability.DISPONIVEL);
            System.out.println("Sucesso ao registrar devolucao: " + rental);
            System.out.println("Total a ser pago: " + rentalService.totalPayment(rental));
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }
}