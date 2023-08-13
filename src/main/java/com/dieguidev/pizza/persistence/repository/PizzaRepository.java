package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //QueryMethods para taer las pizzas disponibles
    

}
