package com.dieguidev.pizza.service.dto;

import lombok.Data;

//DTO es data transfer object

//@Data crea los getters setters constructores obligtorios etc.
@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double newPrice;

}
