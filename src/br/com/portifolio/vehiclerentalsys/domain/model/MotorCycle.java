
package br.com.portifolio.vehiclerentalsys.domain.model;

import br.com.portifolio.vehiclerentalsys.domain.enums.Availability;
import br.com.portifolio.vehiclerentalsys.domain.enums.Categories;
import br.com.portifolio.vehiclerentalsys.domain.exception.DomainException;

public class MotorCycle extends Vehicle {
    public MotorCycle(Integer id, String model, String mark, String plate, Double pricePerDay, Categories categories, Availability availability) throws DomainException {
        super(id, model, mark, plate, pricePerDay, categories, availability);
    }
}
