package com.dieguidev.pizza.web.controller;

import com.dieguidev.pizza.persistence.entity.OrderEntity;
import com.dieguidev.pizza.persistence.projection.OrderSummary;
import com.dieguidev.pizza.service.OrderService;
import com.dieguidev.pizza.service.dto.RandomOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll(){
        return ResponseEntity.ok(this.orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(this.orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(this.orderService.getOutsideOrders());
    }

    @GetMapping("/idCustomer/{idCustomer}")
    public  ResponseEntity<List<OrderEntity>> getOrdersByCustomer(@PathVariable String idCustomer) {
        return ResponseEntity.ok(this.orderService.getOrdersByCustomer(idCustomer));
    }

    @GetMapping("/summary/{id}")
    public  ResponseEntity<OrderSummary> getSummary(@PathVariable int id) {
        return ResponseEntity.ok(this.orderService.getSummary(id));
    }

    @PostMapping("/random")
    public ResponseEntity<Boolean> randomOrder(@RequestBody RandomOrderDto dto) {
        return ResponseEntity.ok(this.orderService.saveRandomOrder(dto));
    }

}















