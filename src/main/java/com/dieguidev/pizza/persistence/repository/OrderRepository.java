package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.OrderEntity;
import com.dieguidev.pizza.persistence.projection.OrderSummary;
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

    @Query(value=
            "SELECT po.id_order                AS idOrder,\n" +
            "         cu.name                    AS customerName,\n" +
            "         po.date                    AS orderDate,\n" +
            "         po.total                   AS orderTotal,\n" +
            "         GROUP_CONCAT(pi.name)     AS pizzaNames\n" +
            "    FROM pizza_order po\n" +
            "         INNER JOIN customer cu ON po.id_customer = cu.id_customer\n" +
            "         INNER JOIN order_item oi ON po.id_order = oi.id_order\n" +
            "         INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza\n" +
            "   WHERE po.id_order = :orderId\n" +
            "GROUP BY po.id_order,\n" +
            "         cu.name,\n" +
            "         po.date,\n" +
            "         po.total", nativeQuery = true)
    OrderSummary findSummary(@Param("orderId") int orderId);
}
