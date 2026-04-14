package br.com.portifolio.vehiclerentalsys.service;

import br.com.portifolio.vehiclerentalsys.domain.entities.Client;
import br.com.portifolio.vehiclerentalsys.domain.entities.Rental;
import br.com.portifolio.vehiclerentalsys.domain.entities.Vehicle;
import br.com.portifolio.vehiclerentalsys.domain.exception.BusinessException;
import br.com.portifolio.vehiclerentalsys.repository.interfaces.RentalDao;

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
            throw new BusinessException("Aluguel ja existe!");
        }
        rentalDao.insertRental(rental);
    }

    public void removeRental(int id) {
        if (rentalDao.findRentalById(id) == null) {
            throw new BusinessException("Aluguel nao existe!");
        }
        rentalDao.removeRental(id);
    }

    public Rental getRentalById(int id) {
        Rental rental = rentalDao.findRentalById(id);
        if (rental == null) {
            throw new BusinessException("Aluguel nao existe!");
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
