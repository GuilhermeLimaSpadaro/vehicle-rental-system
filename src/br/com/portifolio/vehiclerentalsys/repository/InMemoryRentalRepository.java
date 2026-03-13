package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RepositoryException;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

import java.util.Set;
import java.util.TreeSet;

public class InMemoryRentalRepository implements RentalRepositoryInterface {

    private final Set<Rental> setRental = new TreeSet<>();

    public Set<Rental> getSetRental() {
        return setRental;
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
        return setRental.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Aluguel não encontrado."));
    }

    @Override
    public Set<Rental> listContractsRental() {
        return this.setRental;
    }
}
