package br.com.portfolio.vehiclerental.model.entities;

import java.util.Objects;

import br.com.portfolio.vehiclerental.model.enums.Categories;

public class Vehicle {

	private Integer id;
	private String model;
	private String mark;
	private Double price;
	private String plate;
	private boolean rented;
	private Categories categories;

	public Vehicle(Integer id, String model, String mark, Double price, String plate, boolean rented,
			Categories categories) {
		super();
		this.id = id;
		this.model = model;
		this.mark = mark;
		this.price = price;
		this.plate = plate;
		this.rented = rented;
		this.categories = categories;
	}

	public Integer getId() {
		return id;
	}

	public String getModel() {
		return model;
	}

	public String getMark() {
		return mark;
	}

	public Double getPrice() {
		return price;
	}

	public String getPlate() {
		return plate;
	}

	public boolean isRented() {
		return rented;
	}

	public Categories getCategories() {
		return categories;
	}

	@Override
	public String toString() {
		return "ID: " + id + " | Modelo: " + model + " | Marca: " + mark + " | Preco: " + price + " | Placa: " + plate
				+ " | Aluguel: " + rented + " | Categoria: " + categories + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, model, plate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(id, other.id) && Objects.equals(model, other.model) && Objects.equals(plate, other.plate);
	}

}
