package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

import java.util.Set;
import java.util.TreeSet;

public class InMemoryRentalRepository implements RentalRepositoryInterface {

    private final Set<Rental> setRental = new TreeSet<>();

    @Override
    public String toString() {
        return "Aluguel: " + this.setRental + "\n";
    }

    @Override
    public void addRental(Rental rental) throws RepositoryException {
        if (this.setRental.contains(rental)) {
            throw new RepositoryException("Aluguel ja existente.");
        }
        this.setRental.add(rental);
    }

    @Override
    public void removeRental(int id) throws RepositoryException {
        Rental find = this.findRentalById(id);
        this.setRental.remove(find);
    }

    @Override
    public Rental findRentalById(int id) throws RepositoryException {
        for (Rental contractsRental : this.setRental) {
            if (contractsRental.getId().equals(id)) {
                return contractsRental;
            }
        }
        throw new RepositoryException("Aluguel nao encontrado.");
    }

    @Override
    public Set<Rental> listContractsRental() {
        return this.setRental;
    }

}
