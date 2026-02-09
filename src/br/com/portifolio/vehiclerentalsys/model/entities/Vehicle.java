package br.com.portifolio.vehiclerentalsys.model.entities;

import br.com.portifolio.vehiclerentalsys.model.enums.Categories;

import java.util.Objects;

public class Vehicle {
    private final Integer id;
    private final String model;
    private final String mark;
    private final Double price;
    private final String plate;
    private final boolean rented;
    private final Categories categories;

    public Vehicle(Integer id, String model, String mark, Double price, String plate, boolean rented, Categories categories) {
        this.id = id;
        this.model = model;
        this.mark = mark;
        this.price = price;
        this.plate = plate;
        this.rented = rented;
        this.categories = categories;
    }

    public Integer getId() {
        return this.id;
    }

    public String getModel() {
        return this.model;
    }

    public String getMark() {
        return this.mark;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getPlate() {
        return this.plate;
    }

    public boolean isRented() {
        return this.rented;
    }

    public Categories getCategories() {
        return this.categories;
    }

    public String toString() {
        return "ID: " + this.id + " | Modelo: " + this.model + " | Marca: " + this.mark + " | Preco: " + this.price + " | Placa: " + this.plate + " | Aluguel: " + this.rented + " | Categoria: " + String.valueOf(this.categories) + "\n";
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

