package main.java.com.vehiclerental.domain.entities;

import main.java.com.vehiclerental.domain.exception.DomainException;

import java.time.LocalDate;
import java.util.Objects;


public class Rental implements Comparable<Rental> {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Client client;
    private Vehicle vehicle;

    public Rental(Integer id, LocalDate startDate, LocalDate endDate, Client client, Vehicle vehicle) throws DomainException {
        if (id <= 0){
            throw new DomainException("ID invalido. Deve ser maior que zero.");
        }
        this.id = id;
        this.startDate = startDate;
        if (endDate.isBefore(startDate)){
            throw new DomainException("Data de devolucao invalida. Deve ser posterior a data de inicio.");
        }
        this.endDate = endDate;
        if (client == null){
            throw new DomainException("Cliente nao pode ser nulo.");
        }
        this.client = client;
        if (vehicle == null){
            throw new DomainException("Veiculo nao pode ser nulo.");
        }
        this.vehicle = vehicle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return "Contrato de aluguel | ID: " + this.id + " | Saida: " + this.startDate + " | Devolucao: " + this.endDate + " | Detalhes do cliente: " + this.client + " | Detalhes do carro: " + this.vehicle + "\n";
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
