package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

import java.util.Set;

public interface RentalRepositoryInterface {
    void addRental(Rental rental) throws RepositoryException;

    Rental findRentalById(int id) throws RepositoryException;

    void removeRental(int id) throws RepositoryException;

    Set<Rental> listContractsRental();
}
