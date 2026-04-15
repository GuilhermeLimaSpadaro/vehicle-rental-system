package main.java.com.vehiclerental.domain.entities;

import main.java.com.vehiclerental.domain.enums.Availability;
import main.java.com.vehiclerental.domain.enums.Categories;
import main.java.com.vehiclerental.domain.exception.DomainException;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    private Integer id;
    private String model;
    private String mark;
    private String plate;
    private Double pricePerDay;
    private Categories categories;
    private Availability availability;

    public Vehicle() {
    }

    public Vehicle(Integer id, String model, String mark, String plate, Double pricePerDay, Categories categories, Availability availability)  {
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
            throw new DomainException("Placa invalida. Tente novamente");
        }
        this.plate = plate;
        if (pricePerDay == null||pricePerDay <= 0){
            throw new DomainException("Valor nao pode ser nulo!");
        }
        this.pricePerDay = pricePerDay;
        this.categories = categories;
        this.availability = availability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ID: " + this.id +
                " | Modelo: " + this.model + " | Marca: " + this.mark + " | Placa: " + this.plate + " | Preco/Dia: " + this.pricePerDay +
                " | Categorias: " + this.categories + " | Disponibilidade: " + this.availability + '\n';
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

