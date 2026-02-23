package br.com.portifolio.vehiclerentalsys.domain.model;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    private final Integer id;
    private final String model;
    private final String mark;
    private final String plate;
    private final Double pricePerDay;
    private final Categories categories;
    private Availability availability;

    public Vehicle(Integer id, String model, String mark, String plate, Double pricePerDay, Categories categories, Availability availability) throws DomainException {
        if (id <= 0){
            throw new DomainException("ID invalido. Deve ser maior que zero.");
        }
        this.id = id;
        if (model == null||model.isBlank()){
            throw new DomainException("Valor nao pode ser nulo!");
        }
        this.model = model;
        if (mark == null||mark.isBlank()){
            throw new DomainException("Valor nao pode ser nulo!");
        }
        this.mark = mark;
        if (plate == null||!plate.matches("[A-Z]{3}-[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2}")){
            throw new DomainException("Placa invalida. Tente novamente: ");
        }
        this.plate = plate;
        if (pricePerDay == null||pricePerDay <= 0){
            throw new DomainException("Valor nao pode ser nulo!");
        }
        this.pricePerDay = pricePerDay;
        this.categories = categories;
        this.availability = availability;
    }

    public String getPlate() {
        return plate;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                " | Modelo: " + this.model + " | Marca: " + this.mark + " | Placa: " + this.plate + " | Preco/Dia: " + this.pricePerDay +
                " | Categorias: " + this.categories + " | Disponibilidade: " + this.availability + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plate);
    }


    @Override
    public int compareTo(Vehicle other) {
        return this.plate.compareTo(other.getPlate());
    }
}

