
package br.com.portifolio.vehiclerentalsys.domain.model;

import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;

public class Truck extends Vehicle {
    public Truck(Integer id, String model, String mark, String plate, Categories categories, Double pricePerDay) {
        super(id, model, mark, plate, categories, pricePerDay);
    }
}
