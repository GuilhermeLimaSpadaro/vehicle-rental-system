package br.com.portifolio.vehiclerentalsys.model.entities;

import java.time.LocalDate;

public class Rental {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean activeRented;
    private Client client;
    private Vehicle vehicle;

    public Rental(Integer id, LocalDate startDate, LocalDate endDate, boolean activeRented, Client client, Vehicle vehicle) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activeRented = activeRented;
        this.client = client;
        this.vehicle = vehicle;
    }

    public Integer getId() {
        return this.id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public boolean isActiveRented() {
        return this.activeRented;
    }

    public Client getClient() {
        return this.client;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public String toString() {
        return "Aluguel | ID: " + this.id + " | Saida: " + String.valueOf(this.startDate) + " | Devolucao: " + String.valueOf(this.endDate) + " | Disponibilidade: " + this.activeRented + " | Cliente: " + String.valueOf(this.client) + " | Carro: " + String.valueOf(this.vehicle) + "\n";
    }
}
