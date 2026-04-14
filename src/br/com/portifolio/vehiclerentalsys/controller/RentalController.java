package br.com.portifolio.vehiclerentalsys.controller;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.domain.entities.Rental;
import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.service.RentalService;

import java.time.LocalDate;
import java.util.Set;

public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    public void createRental(int id, LocalDate startDate, LocalDate endDate, int clientId, String vehiclePlate) {
        rentalService.createRental(id, startDate, endDate, clientId, vehiclePlate);
    }

    public void removeRental(int id) {
        rentalService.removeRental(id);
    }

    public Rental getRentalById(int id) {
        return rentalService.getRentalById(id);
    }

    public Set<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    public double totalPayment(Rental rental) {
        return rentalService.totalPayment(rental);
    }
}
