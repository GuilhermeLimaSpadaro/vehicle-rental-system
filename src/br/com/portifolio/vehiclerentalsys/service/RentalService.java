package br.com.portifolio.vehiclerentalsys.service;

import br.com.portifolio.vehiclerentalsys.domain.model.Rental;
import br.com.portifolio.vehiclerentalsys.domain.model.Vehicle;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalService {

    private final static double tax = 0.10;

    public double totalPayment(Rental rental) {
        long totalDays = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate());
        LocalDate penalty = LocalDate.now();
        long daysPenalty = ChronoUnit.DAYS.between(rental.getEndDate(), penalty);

        double totalValue = totalDays * rental.getVehicle().getPricePerDay();
        if (daysPenalty > 0){
            return  totalValue + (daysPenalty * rental.getVehicle().getPricePerDay() * tax);
        }else {
            return totalValue;
        }
    }
}
