package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.exception.RentalException;
import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class inMemoryRentalRepository implements RentalRepositoryInterface {

    Set<Rental> setRental = new TreeSet<>();

    public inMemoryRentalRepository() {
    }

    public inMemoryRentalRepository(Set<Rental> setRental) {
        this.setRental = setRental;
    }

    public Set<Rental> getRental() {
        return setRental;
    }

    public void setRental(Set<Rental> rental) {
        this.setRental = rental;
    }

    @Override
    public String toString() {
        return "Rental: " + setRental + "\n";
    }

    @Override
    public void addRental(Rental rental) {
        if (setRental.contains(rental)) {
            throw new RentalException("Contrato ja existente.");
        }
        setRental.add(rental);
    }

    @Override
    public void removeRental(Rental rental) {
        setRental.remove(rental);
    }

    public Rental findRentalById(int id) {
        if (setRental.isEmpty()) {
            throw new RentalException("Lista nao pode ser vazia");
        } else {
            for (Rental rentalObj : setRental) {
                if (rentalObj.getId().equals(id)) {
                    return rentalObj;
                }
            }
        }
        throw new RentalException("Aluguel nao encontrado.");
    }
}
