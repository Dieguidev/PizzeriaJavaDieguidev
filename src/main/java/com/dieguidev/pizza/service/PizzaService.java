package com.dieguidev.pizza.service;

import com.dieguidev.pizza.persistence.entity.PizzaEntity;
import com.dieguidev.pizza.persistence.repository.PizzaPagSortRepository;
import com.dieguidev.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
//JdbcTemplate nos permite enviar una consulta sql para convertirlo en clase de java

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public Page<PizzaEntity> getAll(int page, int elements) {
        PageRequest pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getByAvailable(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getById(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity getByName(String namePizza){
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(namePizza).orElseThrow(()->new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getByIngredient(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getByNotIngredient(String description) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(description);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity save(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    //este metodo nos ayuda a saber si existe o no uuna pizza
    public boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }


}






















