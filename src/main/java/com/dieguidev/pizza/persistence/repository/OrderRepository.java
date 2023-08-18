package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    //retorna ordenes del dia
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);

    //retorna ordenes fuera del establecimiento
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    //usando @Query con SQL nativo
    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);


}
