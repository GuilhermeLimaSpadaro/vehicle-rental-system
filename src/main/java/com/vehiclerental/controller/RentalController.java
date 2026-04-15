package main.java.com.vehiclerental.controller;

import main.java.com.vehiclerental.domain.entities.Rental;
import main.java.com.vehiclerental.service.RentalService;

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
