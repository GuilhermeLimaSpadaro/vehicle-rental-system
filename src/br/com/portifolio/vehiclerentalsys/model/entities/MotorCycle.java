
package br.com.portifolio.vehiclerentalsys.model.entities;

import br.com.portifolio.vehiclerentalsys.model.enums.Categories;

public class MotorCycle extends Vehicle {
    public MotorCycle(Integer id, String model, String mark, Double price, String plate, boolean rented, Categories categories) {
        super(id, model, mark, price, plate, rented, categories);
    }
}
