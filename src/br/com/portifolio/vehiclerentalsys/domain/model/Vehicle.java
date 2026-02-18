package br.com.portifolio.vehiclerentalsys.domain.model;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;

import java.util.Objects;

public class Vehicle {
    private final Integer id;
    private final String model;
    private final String mark;
    private final String plate;
    private final Double pricePerDay;
    private final Categories categories;
    private final Availability availability;

    public Vehicle(Integer id, String model, String mark, String plate, Double pricePerDay, Categories categories, Availability availability) {
        this.id = id;
        this.model = model;
        this.mark = mark;
        this.plate = plate;
        this.pricePerDay = pricePerDay;
        this.categories = categories;
        this.availability = availability;
    }

    public Integer getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }

    public String getPlate() {
        return this.plate;
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                " | Modelo: " + this.model + " | Marca: " + this.mark + " | Placa: " + this.plate + " | Preco/Dia" + this.pricePerDay +
                " | Categorias" + this.categories + " | Disponibilidade: " + this.availability + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) && Objects.equals(model, vehicle.model) && Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, plate);
    }
}

