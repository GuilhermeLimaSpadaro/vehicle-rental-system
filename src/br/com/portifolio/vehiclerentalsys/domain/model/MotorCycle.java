
package br.com.portifolio.vehiclerentalsys.domain.model;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;

public class MotorCycle extends Vehicle {
    public MotorCycle(Integer id, String model, String mark, String plate, Double pricePerDay, Categories categories, Availability availability) {
        super(id, model, mark, plate, pricePerDay, categories, availability);
    }
}
