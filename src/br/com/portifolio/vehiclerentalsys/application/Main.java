
package br.com.portifolio.vehiclerentalsys.application;

import br.com.portifolio.vehiclerentalsys.model.entities.Client;
import br.com.portifolio.vehiclerentalsys.model.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.model.enums.Categories;
import br.com.portifolio.vehiclerentalsys.model.exception.ClientException;
import br.com.portifolio.vehiclerentalsys.model.exception.VehicleException;
import br.com.portifolio.vehiclerentalsys.model.interfaces.ClientInterface;
import br.com.portifolio.vehiclerentalsys.model.interfaces.VehicleInterface;
import br.com.portifolio.vehiclerentalsys.repository.ClientRepository;
import br.com.portifolio.vehiclerentalsys.repository.VehicleRepository;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        VehicleInterface vehicleRepo = new VehicleRepository();
        ClientInterface clientRepo = new ClientRepository();

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
                        findClientByName(input, clientRepo);
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
                        findVehicleByName(input, vehicleRepo);
                        break;
                    case 8:
                        listVehicles(vehicleRepo);
                }
            }
        }

    }

    public static void menu() {
        System.out.println();
        System.out.printf("=========================" + System.lineSeparator()
                + "       MENU PRINCIPAL" + System.lineSeparator()
                + "=========================" + System.lineSeparator());
        System.out.println("1. Cadastrar cliente.");
        System.out.println("2. Remover cliente.");
        System.out.println("3. Buscar cliente.");
        System.out.println("4. Listar clientes.");
        System.out.println("----------------------");
        System.out.println("5. Cadastrar veiculo.");
        System.out.println("6. Remover veiculo.");
        System.out.println("7. Buscar veiculo.");
        System.out.println("8. Listar veiculos");
        System.out.println("----------------------");
        System.out.println("0. Sair.");
        System.out.print(System.lineSeparator() + "Escolha uma das opcoes acima: ");
    }

    public static int intValidation(Scanner input) {
        while (true) {
            try {
                String line = input.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception var2) {
                System.out.print("Por favor, digite um numero: ");
            }
        }
    }

    public static double doubleValidation(Scanner input) {
        while (true) {
            try {
                String line = input.nextLine().trim();
                return Double.parseDouble(line);
            } catch (Exception var2) {
                System.out.print("Por favor, digite um numero: ");
            }
        }
    }

    public static Categories validationEnum(Scanner input) {
        while (true) {
            try {
                String line = input.nextLine().toUpperCase();
                return Categories.valueOf(line);
            } catch (Exception var2) {
                System.out.print("Insira uma categoria! Car | MotorCycle | Truck: ");
            }
        }
    }

    public static void addVehicle(Scanner input, VehicleInterface vehicleRepo) {
        System.out.println();
        System.out.print("ID: ");
        int id = intValidation(input);
        System.out.print("Modelo: ");
        String model = input.nextLine();
        System.out.print("Marca: ");
        String mark = input.nextLine();
        System.out.print("Preco: ");
        double price = doubleValidation(input);
        System.out.print("Placa: ");
        String plate = input.nextLine();
        System.out.print("Categoria: ");
        Categories categories = validationEnum(input);
        try {
            Vehicle vehicle = new Vehicle(id, model, mark, price, plate, false, categories);
            vehicleRepo.addVehicle(vehicle);
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeVehicle(Scanner input, VehicleInterface vehicleRepo) {
        System.out.print("Por favor, insira o modelo do carro que deseja remover: ");
        String model = input.nextLine();
        try {
            vehicleRepo.removeVehicle(model);
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findVehicleByName(Scanner input, VehicleInterface vehicleRepo) {
        System.out.println("Insira o nome do veiculo que deseja: ");
        String name = input.nextLine();
        try {
            Vehicle vehicle = vehicleRepo.findVehicleByName(name);
            System.out.println(vehicle);
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listVehicles(VehicleInterface vehicleRepo) {
        try {
            List<Vehicle> vehicles = vehicleRepo.listVehicles();
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        } catch (VehicleException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addClient(Scanner input, ClientInterface clientRepo) {
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

    public static void removeClient(Scanner input, ClientInterface clientRepo) {
        System.out.print("Por favor, insira o nome que deseja remover: ");
        String name = input.nextLine();
        try {
            clientRepo.removeClient(name);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void findClientByName(Scanner input, ClientInterface clientRepo) {
        System.out.print("Insira o nome que deseja: ");
        String name = input.nextLine();
        System.out.println();
        try {
            Client client = clientRepo.findClientByName(name);
            System.out.println(client);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listClients(ClientInterface clientRepo) {
        try {
            Set<Client> clients = clientRepo.listClients();
            System.out.println(clients);
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
