package br.com.portifolio.vehiclerentalsys;

import br.com.portifolio.vehiclerentalsys.controller.ClientController;
import br.com.portifolio.vehiclerentalsys.controller.RentalController;
import br.com.portifolio.vehiclerentalsys.controller.VehicleController;
import br.com.portifolio.vehiclerentalsys.ui.ClientUI;
import br.com.portifolio.vehiclerentalsys.ui.RentalUI;
import br.com.portifolio.vehiclerentalsys.ui.VehicleUI;
import br.com.portifolio.vehiclerentalsys.db.DataBase;
import br.com.portifolio.vehiclerentalsys.repository.factory.DaoFactory;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.ClientDao;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.RentalDao;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.VehicleDao;
import br.com.portifolio.vehiclerentalsys.service.ClientService;
import br.com.portifolio.vehiclerentalsys.service.RentalService;
import br.com.portifolio.vehiclerentalsys.service.VehicleService;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

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
        RentalService rentalService = new RentalService(rentalDao);

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
                    case 13:
                        rentalUI.vehicleReturn(input);
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
        System.out.println("11. Buscar aluguel.");
        System.out.println("12. Listar alugueis.");
        System.out.println("13. Registrar devolucao.");
        System.out.println("0.  Sair.");
        System.out.print(System.lineSeparator() + "Escolha uma das opcoes acima: ");
    }
}