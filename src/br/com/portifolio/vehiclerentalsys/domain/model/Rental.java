package br.com.portifolio.vehiclerentalsys.domain.model;

import java.time.LocalDate;
import java.util.Objects;


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

    @Override
    public String toString() {
        return "Contrator de aluguel | ID: " + this.id + " | Saida: " + this.startDate + " | Devolucao: " + this.endDate + " | Detalhes do cliente: " + this.client + " | Detalhes do carro: " + this.vehicle + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(id, rental.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Rental otherRental) {
        return this.id.compareTo(otherRental.id);
    }
}
