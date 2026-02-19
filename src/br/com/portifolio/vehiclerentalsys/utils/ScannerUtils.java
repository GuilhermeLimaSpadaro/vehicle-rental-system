package br.com.portifolio.vehiclerentalsys.utils;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;

import java.util.Scanner;

public class ScannerUtils {

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
}
