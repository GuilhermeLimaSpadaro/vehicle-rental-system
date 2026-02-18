package br.com.portifolio.vehiclerentalsys;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.exception.RentalException;
import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.ClientException;
import br.com.portifolio.vehiclerentalsys.domain.exception.VehicleException;
import br.com.portifolio.vehiclerentalsys.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        VehicleRepositoryInterface vehicleRepo = new InMemoryVehicleRepository();
        ClientRepositoryInterface clientRepo = new InMemoryClientRepository();
        RentalRepositoryInterface rentalRepo = new InMemoryRentalRepository();

        try (Scanner input = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                menu();
                switch (intValidation(input)) {
                    case 0:
                        System.out.println();
                        System.out.println("Saindo..");
                        running = false;
                        break;
                    case 1:
                        addClient(input, clientRepo);
                        break;
                    case 2:
                        removeClient(input, clientRepo);
                        break;
                    case 3:
                        findClientById(input, clientRepo);
                        break;
                    case 4:
                        listClients(clientRepo);
                        break;
                    case 5:
                        addVehicle(input, vehicleRepo);
                        break;
                    case 6:
                        removeVehicle(input, vehicleRepo);
                        break;
                    case 7:
                        findVehicleByPlate(input, vehicleRepo);
                        break;
                    case 8:
                        listVehicles(vehicleRepo);
                        break;
                    case 9:
                        addRental(input, clientRepo, vehicleRepo, rentalRepo);
                        break;
                    case 10:
                        removeRental(input, rentalRepo);
                        break;
                    case 11:
                        findRentalById(input, rentalRepo);
                        break;
                    case 12:
                        listRental(rentalRepo);
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

    /**
     * Validation
     **/

    public static int intValidation(Scanner input) {
        while (true) {
            try {
                String line = input.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception e) {
                System.out.print("Por favor, insira um numero: ");
            }
        }
    }

    public static double doubleValidation(Scanner input) {
        while (true) {
            try {
                String line = input.nextLine().trim();
                return Double.parseDouble(line);
            } catch (Exception e) {
                System.out.print("Por favor, insira um numero: ");
            }
        }
    }

    public static Categories categoriesEnum(Scanner input) {
        while (true) {
            try {
                String categories = input.nextLine().trim().toUpperCase();
                return Categories.valueOf(categories);
            } catch (IllegalArgumentException e) {
                System.out.println("Opcao invalida.");
                System.out.println("Digite: Carro | Moto | Caminhao");
            }
        }
    }

    public static Availability availabilityEnum(Scanner input) {
        while (true) {
            try {
                String availability = input.nextLine().trim().toUpperCase();
                return Availability.valueOf(availability);
            } catch (IllegalArgumentException e) {
                System.out.println("Opcao invalida.");
                System.out.println("Digite: DISPONIVEL | ALUGADO | MANUTENCAO;");
            }
        }
    }

    /**
     * Vehicle Methods
     **/

    public static void addVehicle(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        System.out.println();
        System.out.print("ID: ");
        int id = intValidation(input);
        System.out.print("Modelo: ");
        String model = input.nextLine();
        System.out.print("Marca: ");
        String mark = input.nextLine();
        System.out.print("Placa: ");
        String plate = input.nextLine();
        System.out.print("Preco/Dia: ");
        double price = doubleValidation(input);
        System.out.print("Categoria: ");
        Categories categories = categoriesEnum(input);
        System.out.println("Disponibilidade: ");
        Availability availability = availabilityEnum(input);
        try {
            Vehicle vehicle = new Vehicle(id, model, mark, plate, price, categories, availability);
            vehicleRepo.addVehicle(vehicle);
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeVehicle(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        System.out.print("Informe o modelo do carro que deseja remover: ");
        String model = input.nextLine();
        try {
            vehicleRepo.removeVehicle(model);
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findVehicleByPlate(Scanner input, VehicleRepositoryInterface vehicleRepo) {
        System.out.println("Informe o nome do veiculo que deseja: ");
        String name = input.nextLine();
        try {
            Vehicle vehicle = vehicleRepo.findVehicleByPlate(name);
            System.out.println(vehicle);
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listVehicles(VehicleRepositoryInterface vehicleRepo) {
        List<Vehicle> vehicles = vehicleRepo.listVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

    }

    /**
     * Client Methods
     **/

    public static void addClient(Scanner input, ClientRepositoryInterface clientRepo) {
        System.out.println();
        System.out.print("ID: ");
        int id = intValidation(input);
        System.out.print("Nome: ");
        String name = input.nextLine();
        System.out.print("CPF: ");
        String document = input.nextLine();
        System.out.print("Telefone: ");
        String phone = input.nextLine();
        try {
            Client client = new Client(id, name, document, phone);
            clientRepo.add(client);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeClient(Scanner input, ClientRepositoryInterface clientRepo) {
        System.out.print("Informe o ID: ");
        int id = intValidation(input);
        try {
            clientRepo.removeClient(id);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findClientById(Scanner input, ClientRepositoryInterface clientRepo) {
        System.out.print("Informe o ID: ");
        int id = intValidation(input);
        System.out.println();
        try {
            Client client = clientRepo.findClientById(id);
            System.out.println(client);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listClients(ClientRepositoryInterface clientRepo) {
        Set<Client> clients = clientRepo.listClients();
        System.out.println(clients);
    }

    /**
     * Rental Methods
     **/

    public static void addRental(Scanner input, ClientRepositoryInterface clientRepo, VehicleRepositoryInterface vehicleRepo, RentalRepositoryInterface rentalRepo) {
        System.out.println();
        System.out.print("ID: ");
        int rentalId = intValidation(input);
        System.out.println("Data de saida registrada.");
        LocalDate departureDate = LocalDate.now();
        System.out.print("Data de entrada: ");
        String dateOfEntry = input.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate entryDate = LocalDate.parse(dateOfEntry, dtf);
        try {
            System.out.print("Informe o ID do cliente: ");
            int id = intValidation(input);
            Client client = clientRepo.findClientById(id);
            System.out.print("Informe a placa do veiculo: ");
            String plate = input.nextLine();
            Vehicle vehicle = vehicleRepo.findVehicleByPlate(plate);
            Rental rental = new Rental(rentalId, departureDate, entryDate, client, vehicle);
            rentalRepo.addRental(rental);
        } catch (ClientException | VehicleException | RentalException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeRental(Scanner input, RentalRepositoryInterface rentalRepo) {
        System.out.println("Informe o ID: ");
        int id = intValidation(input);
        try {
            rentalRepo.removeRental(id);
        } catch (RentalException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findRentalById(Scanner input, RentalRepositoryInterface rentalRepo) {
        System.out.println("Informe o ID: ");
        int id = intValidation(input);
        try {
            Rental rental = rentalRepo.findRentalById(id);
            System.out.println(rental);
        } catch (RentalException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listRental(RentalRepositoryInterface rentalRepo) {
        System.out.println(rentalRepo.listContractsRental());
    }

}
