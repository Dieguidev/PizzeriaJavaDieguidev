package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.PizzaEntity;
import com.dieguidev.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

    //Usando @Modifying en un @Query
    //Si queremos que un query haga update create delete se necesita @Modidifying
    @Query(value =
            "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);

}
