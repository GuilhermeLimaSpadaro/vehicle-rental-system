package br.com.portifolio.vehiclerentalsys.domain.model;

import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;

import java.util.Objects;

public class Vehicle {
    private final Integer id;
    private final String model;
    private final String mark;
    private final String plate;
    private final Categories categories;
    private final Double pricePerDay;

    public Vehicle(Integer id, String model, String mark, String plate, Categories categories, Double pricePerDay) {
        this.id = id;
        this.model = model;
        this.mark = mark;
        this.plate = plate;
        this.categories = categories;
        this.pricePerDay = pricePerDay;
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public String toString() {
        return "ID: " + this.id + " | Modelo: " + this.model + " | Marca: " + this.mark + " | Preco: " + this.pricePerDay + " | Placa: " + this.plate + " | Categoria: " + String.valueOf(this.categories) + "\n";
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

