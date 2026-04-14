package br.com.portifolio.vehiclerentalsys.ui.console;

import br.com.portifolio.vehiclerentalsys.controller.ClientController;
import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.util.Scanner;
import java.util.Set;

public class ClientUI {
    private final ClientController clientController;

    public ClientUI(ClientController clientController) {
        this.clientController = clientController;
    }

    public void createClient(Scanner input) {
        try {
            System.out.println();
            System.out.print("ID: ");
            int id = ScannerUtils.intValidation(input);
            System.out.print("Nome: ");
            String name = input.nextLine();
            System.out.print("CPF: ");
            String document = input.nextLine();
            System.out.print("Telefone: ");
            String phone = input.nextLine();
            clientController.createClient(id, name, document, phone);
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeClient(Scanner input) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            clientController.removeClient(id);
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getClientById(Scanner input) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            System.out.println(clientController.getClientById(id));
        } catch (DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listClients() {
        Set<Client> clientSet = clientController.getAllClients();
        clientSet.forEach(System.out::println);
    }
}