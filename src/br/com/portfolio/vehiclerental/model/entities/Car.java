package br.com.portfolio.vehiclerental.model.entities;

import br.com.portfolio.vehiclerental.model.enums.Categories;

public class Car extends Vehicle {

	public Car(Integer id, String model, String mark, Double price, String plate, boolean rented,
			Categories categories) {
		super(id, model, mark, price, plate, rented, categories);
	}

}
