package br.com.portifolio.vehiclerentalsys.repository.interfaces;

import br.com.portifolio.vehiclerentalsys.domain.entities.Rental;

import java.util.Set;

public interface RentalDao {
    void insertRental(Rental rental);

    Rental findRentalById(int id);

    void removeRental(int id);

    Set<Rental> findAllRental();
}
