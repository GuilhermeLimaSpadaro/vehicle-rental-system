package br.com.portifolio.vehiclerentalsys.controller;

import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;
import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Client;
import br.com.portifolio.vehiclerentalsys.repository.ClientRepositoryInterface;
import br.com.portifolio.vehiclerentalsys.utils.ScannerUtils;

import java.util.Scanner;
import java.util.Set;

public class ClientController {

    public void addClient(Scanner input, ClientRepositoryInterface clientRepo) {
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
            Client client = new Client(id, name, document, phone);
            clientRepo.add(client);
        } catch (RepositoryException | DomainException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeClient(Scanner input, ClientRepositoryInterface clientRepo) {
        try {
            System.out.print("Informe o ID: ");
            int id = ScannerUtils.intValidation(input);
            clientRepo.removeClient(id);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void findClientById(Scanner input, ClientRepositoryInterface clientRepo) {
        try {
        System.out.print("Informe o ID: ");
        int id = ScannerUtils.intValidation(input);
        System.out.println();
            Client client = clientRepo.findClientById(id);
            System.out.println(client);
        } catch (RepositoryException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listClients(ClientRepositoryInterface clientRepo) {
        Set<Client> clients = clientRepo.listClients();
        if (clients.isEmpty()) {
            System.out.println("Lista de clientes vazia!");
        } else {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }
}