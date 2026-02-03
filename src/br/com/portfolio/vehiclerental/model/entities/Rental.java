package br.com.portfolio.vehiclerental.model.entities;

import java.time.LocalDate;

public class Rental {

	private Integer id;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean activeRented;
	private Client client;
	private Vehicle vehicle;

	public Rental(Integer id, LocalDate startDate, LocalDate endDate, boolean activeRented, Client client,
			Vehicle vehicle) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activeRented = activeRented;
		this.client = client;
		this.vehicle = vehicle;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public boolean isActiveRented() {
		return activeRented;
	}

	public Client getClient() {
		return client;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	@Override
	public String toString() {
		return "Aluguel | ID: " + id + " | Saida: " + startDate + " | Devolucao: " + endDate + " | Disponibilidade: "
				+ activeRented + " | Cliente: " + client + " | Carro: " + vehicle + "\n";
	}
}
