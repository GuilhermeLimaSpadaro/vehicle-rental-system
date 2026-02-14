package br.com.portifolio.vehiclerentalsys.repository;

import br.com.portifolio.vehiclerentalsys.domain.model.Rental;

public interface RentalRepositoryInterface {

    public void addRental(Rental rental);

    public void removeRental(Rental rental);
}
