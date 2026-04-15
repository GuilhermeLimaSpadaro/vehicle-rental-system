package main.java.com.vehiclerental.application;

import main.java.com.vehiclerental.controller.ClientController;
import main.java.com.vehiclerental.controller.RentalController;
import main.java.com.vehiclerental.controller.VehicleController;
import main.java.com.vehiclerental.view.ClientUI;
import main.java.com.vehiclerental.view.RentalUI;
import main.java.com.vehiclerental.view.VehicleUI;
import main.java.com.vehiclerental.infrastructure.database.DataBase;
import main.java.com.vehiclerental.infrastructure.factory.DaoFactory;
import main.java.com.vehiclerental.repository.ClientDao;
import main.java.com.vehiclerental.repository.RentalDao;
import main.java.com.vehiclerental.repository.VehicleDao;
import main.java.com.vehiclerental.service.ClientService;
import main.java.com.vehiclerental.service.RentalService;
import main.java.com.vehiclerental.service.VehicleService;
import main.java.com.vehiclerental.utils.ScannerUtils;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Connection conn = DataBase.getConnection();

        DaoFactory daoFactory = new DaoFactory();
        ClientDao clientDao = daoFactory.createClientConnection(conn);
        VehicleDao vehicleDao = daoFactory.createVehicleConnection(conn);
        RentalDao rentalDao = daoFactory.createRentalConnection(conn);

        ClientService clientService = new ClientService(clientDao);
        VehicleService vehicleService = new VehicleService(vehicleDao);
        RentalService rentalService = new RentalService(rentalDao, clientService, vehicleService);

        ClientController clientController = new ClientController(clientService);
        VehicleController vehicleController = new VehicleController(vehicleService);
        RentalController rentalController = new RentalController(rentalService);

        ClientUI clientUI = new ClientUI(clientController);
        VehicleUI vehicleUI = new VehicleUI(vehicleController);
        RentalUI rentalUI = new RentalUI(rentalController);

        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;

            while (running) {

                menu();
                switch (ScannerUtils.intValidation(input)) {
                    case 0:
                        System.out.println();
                        System.out.println("Saindo..");
                        DataBase.closeConnection();
                        running = false;
                        break;
                    case 1:
                        clientUI.createClient(input);
                        break;
                    case 2:
                        clientUI.removeClient(input);
                        break;
                    case 3:
                        clientUI.getClientById(input);
                        break;
                    case 4:
                        clientUI.listClients();
                        break;
                    case 5:
                        vehicleUI.createVehicle(input);
                        break;
                    case 6:
                        vehicleUI.removeVehicle(input);
                        break;
                    case 7:
                        vehicleUI.findVehicleByPlate(input);
                        break;
                    case 8:
                        vehicleUI.listVehicles();
                        break;
                    case 9:
                        rentalUI.createRental(input);
                        break;
                    case 10:
                        rentalUI.removeRental(input);
                        break;
                    case 11:
                        rentalUI.findRentalById(input);
                        break;
                    case 12:
                        rentalUI.listRental();
                        break;
                    default:
                        System.out.println("Opcao invalida, Tente novamente!");
                        break;
                }
            }
        }

    }

    public static void menu() {
        System.out.println();
        System.out.println("=========================       MENU PRINCIPAL       =========================");
        System.out.println("1.  Criar cliente.");
        System.out.println("2.  Remover cliente.");
        System.out.println("3.  Buscar cliente.");
        System.out.println("4.  Listar clientes.");
        System.out.println("----------------------");
        System.out.println("5.  Criar veiculo.");
        System.out.println("6.  Remover veiculo.");
        System.out.println("7.  Buscar veiculo.");
        System.out.println("8.  Listar veiculos.");
        System.out.println("----------------------");
        System.out.println("9.  Criar aluguel.");
        System.out.println("10. Excluir aluguel");
        System.out.println("11. Buscar aluguel.");
        System.out.println("12. Listar alugueis.");
        System.out.println("0.  Sair.");
        System.out.print("Escolha uma das opcoes acima: ");
    }
}