package main.java.com.vehiclerental.repository;

import main.java.com.vehiclerental.domain.entities.Rental;

import java.util.Set;

public interface RentalDao {
    void insertRental(Rental rental);

    Rental findRentalById(int id);

    void removeRental(int id);

    Set<Rental> findAllRental();
}
