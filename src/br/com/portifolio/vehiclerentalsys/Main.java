package br.com.portifolio.vehiclerentalsys;

import br.com.portifolio.vehiclerentalsys.controller.ClientController;
import br.com.portifolio.vehiclerentalsys.controller.RentalController;
import br.com.portifolio.vehiclerentalsys.controller.VehicleController;
import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.repository.*;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws DomainException {
        Locale.setDefault(Locale.US);
        VehicleRepositoryInterface vehicleRepo = new InMemoryVehicleRepository();
        ClientRepositoryInterface clientRepo = new InMemoryClientRepository();
        RentalRepositoryInterface rentalRepo = new InMemoryRentalRepository();
        ClientController clientController = new ClientController();
        RentalController rentalController = new RentalController();
        VehicleController vehicleController = new VehicleController();

        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                menu();
                switch (ScannerUtils.intValidation(input)) {
                    case 0:
                        System.out.println();
                        System.out.println("Saindo..");
                        running = false;
                        break;
                    case 1:
                        clientController.addClient(input, clientRepo);
                        break;
                    case 2:
                        clientController.removeClient(input, clientRepo);
                        break;
                    case 3:
                        clientController.findClientById(input, clientRepo);
                        break;
                    case 4:
                        clientController.listClients(clientRepo);
                        break;
                    case 5:
                        vehicleController.addVehicle(input, vehicleRepo);
                        break;
                    case 6:
                        vehicleController.removeVehicle(input, vehicleRepo);
                        break;
                    case 7:
                        vehicleController.findVehicleByPlate(input, vehicleRepo);
                        break;
                    case 8:
                        vehicleController.listVehicles(vehicleRepo);
                        break;
                    case 9:
                        rentalController.addRental(input, clientRepo, vehicleRepo, rentalRepo);
                        break;
                    case 10:
                        rentalController.removeRental(input, rentalRepo);
                        break;
                    case 11:
                        rentalController.findRentalById(input, rentalRepo);
                        break;
                    case 12:
                        rentalController.listRental(rentalRepo);
                        break;
                }
            }
        }

    }

    public static void menu() {
        System.out.println();
        System.out.printf("=========================" + System.lineSeparator() + "       MENU PRINCIPAL" + System.lineSeparator() + "=========================" + System.lineSeparator());
        System.out.println("1.  Cadastrar cliente.");
        System.out.println("2.  Remover cliente.");
        System.out.println("3.  Buscar cliente.");
        System.out.println("4.  Listar clientes.");
        System.out.println("----------------------");
        System.out.println("5.  Cadastrar veiculo.");
        System.out.println("6.  Remover veiculo.");
        System.out.println("7.  Buscar veiculo.");
        System.out.println("8.  Listar veiculos.");
        System.out.println("----------------------");
        System.out.println("9.  Alugar veiculo.");
        System.out.println("10. Excluir aluguel");
        System.out.println("11. Buscar aluguel");
        System.out.println("12. Listar alugueis");
        System.out.println("0.  Sair.");
        System.out.print(System.lineSeparator() + "Escolha uma das opcoes acima: ");
    }
}
