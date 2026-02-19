package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

import java.util.Set;
import java.util.TreeSet;

public class InMemoryRentalRepository implements RentalRepositoryInterface {

    Set<Rental> setRental = new TreeSet<>();

    public InMemoryRentalRepository() {
    }

    public InMemoryRentalRepository(Set<Rental> setRental) {
        this.setRental = setRental;
    }

    public Set<Rental> getRental() {
        return this.setRental;
    }

    public void setRental(Set<Rental> rental) {
        this.setRental = rental;
    }

    @Override
    public String toString() {
        return "Rental: " + this.setRental + "\n";
    }

    @Override
    public void addRental(Rental rental) throws RepositoryException {
        if (this.setRental.contains(rental)) {
            throw new RepositoryException("Contrato ja existente.");
        }
        this.setRental.add(rental);
    }

    @Override
    public void removeRental(int id) throws  RepositoryException {
        Rental find = this.findRentalById(id);
        this.setRental.remove(find);
    }

    @Override
    public Rental findRentalById(int id) throws RepositoryException {
        for (Rental rentalObj : this.setRental) {
            if (rentalObj.getId().equals(id)) {
                return rentalObj;
            }
        }
        throw new RepositoryException("Aluguel nao encontrado.");
    }

    @Override
    public Set<Rental> listContractsRental() {
        return this.setRental;
    }

}
