package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RentalException;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

import java.util.Set;

public interface RentalRepositoryInterface {

    void addRental(Rental rental) throws RentalException;

    Rental findRentalById(int id) throws RentalException;

    void removeRental(int id) throws RentalException;

    Set<Rental> listContractsRental();
}
