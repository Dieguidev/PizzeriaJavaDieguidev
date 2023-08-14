package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    //retorna ordenes del dia
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    //retorna ordenes fuera del establecimiento
    List<OrderEntity> findAllByMethodIn(List<String> methods);


}
