package com.dieguidev.pizza.web.controller;

import com.dieguidev.pizza.persistence.entity.PizzaEntity;
import com.dieguidev.pizza.service.PizzaService;
import com.dieguidev.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int idPizza) {
        return ResponseEntity.ok(this.pizzaService.getById(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getByAvailable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int elements,
                                                            @RequestParam(defaultValue = "price") String sortBy,
                                                            @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.pizzaService.getByAvailable(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/name/{namePizza}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String namePizza) {
        return ResponseEntity.ok(this.pizzaService.getByName(namePizza));
    }

    @GetMapping("/ingredient/{ingredientPizza}")
    public ResponseEntity<List<PizzaEntity>> getByIngredient(@PathVariable String ingredientPizza) {
        return ResponseEntity.ok((this.pizzaService.getByIngredient(ingredientPizza)));
    }

    @GetMapping("/notingredient/{ingredientPizza}")
    public ResponseEntity<List<PizzaEntity>> getByNotIngredient(@PathVariable String ingredientPizza) {
        return ResponseEntity.ok((this.pizzaService.getByNotIngredient(ingredientPizza)));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price) {
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> createNewPizza(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
        if (this.pizzaService.exists(dto.getPizzaId())) {
            this.pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete (@PathVariable int idPizza) {
        if (this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}















