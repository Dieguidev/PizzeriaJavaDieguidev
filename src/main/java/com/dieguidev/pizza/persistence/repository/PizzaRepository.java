package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    //QueryMethods para taer las pizzas disponibles

    //query para traer las disponibles por orden de precio
    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    //query para traer la disponible por nombre
    Optional<PizzaEntity>  findFirstByAvailableTrueAndNameIgnoreCase(String name);

    //query para traer las disponibles por ingrediente
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    //query para traer las disponibles que no contengan un ingrediente
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    //retorna cuantas pizzas vegeanas ofrecemos
    int countByVeganTrue();

    //las 3 pizzas mas baratas
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);



}
