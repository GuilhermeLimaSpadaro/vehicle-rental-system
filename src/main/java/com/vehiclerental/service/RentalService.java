package main.java.com.vehiclerental.service;

import main.java.com.vehiclerental.domain.entities.Client;
import main.java.com.vehiclerental.domain.entities.Rental;
import main.java.com.vehiclerental.domain.entities.Vehicle;
import main.java.com.vehiclerental.domain.exception.DomainException;
import main.java.com.vehiclerental.repository.RentalDao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class RentalService {
    private final RentalDao rentalDao;
    private final ClientService clientService;
    private final VehicleService vehicleService;
    private final static double tax = 0.10;

    public RentalService(RentalDao rentalDao, ClientService clientService, VehicleService vehicleService) {
        this.rentalDao = rentalDao;
        this.clientService = clientService;
        this.vehicleService = vehicleService;
    }

    public void createRental(int id, LocalDate startDate, LocalDate endDate, int clientId, String vehiclePlate) {
        Client client = clientService.getClientById(clientId);
        Vehicle vehicle = vehicleService.getVehicleByPlate(vehiclePlate);
        Rental rental = new Rental(id, startDate, endDate, client, vehicle);
        if (rentalDao.findRentalById(rental.getId()) != null) {
            throw new DomainException("Aluguel ja existe!");
        }
        rentalDao.insertRental(rental);
    }

    public void removeRental(int id) {
        if (rentalDao.findRentalById(id) == null) {
            throw new DomainException("Aluguel nao existe!");
        }
        rentalDao.removeRental(id);
    }

    public Rental getRentalById(int id) {
        Rental rental = rentalDao.findRentalById(id);
        if (rental == null) {
            throw new DomainException("Aluguel nao existe!");
        }
        return rental;
    }

    public Set<Rental> getAllRentals() {
        return rentalDao.findAllRental();
    }

    public double totalPayment(Rental rental) {
        long totalDays = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        LocalDate penalty = LocalDate.now();
        long daysPenalty = ChronoUnit.DAYS.between(rental.getEndDate(), penalty);

        double totalValue = totalDays * rental.getVehicle().getPricePerDay();
        if (daysPenalty > 0) {
            return totalValue + (daysPenalty * rental.getVehicle().getPricePerDay() * tax);
        } else {
            return totalValue;
        }
    }
}
