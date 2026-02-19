package br.com.portifolio.vehiclerentalsys.domain.model;

import java.time.LocalDate;


public class Rental implements Comparable<Rental> {
    private final Integer id;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Client client;
    private final Vehicle vehicle;

    public Rental(Integer id, LocalDate startDate, LocalDate endDate, Client client, Vehicle vehicle) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.vehicle = vehicle;
    }

    public Integer getId() {
        return this.id;
    }

    public String toString() {
        return "Contrator de aluguel | ID: " + this.id + " | Saida: " + String.valueOf(this.startDate) + " | Devolucao: " + String.valueOf(this.endDate) + " | Detalhes do cliente: " + String.valueOf(this.client) + " | Detalhes do carro: " + String.valueOf(this.vehicle) + "\n";
    }

    @Override
    public int compareTo(Rental otherRental) {
        return this.id.compareTo(otherRental.id);
    }
}
