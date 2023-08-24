package com.dieguidev.pizza.web.controller;

import com.dieguidev.pizza.persistence.entity.CustomerEntity;
import com.dieguidev.pizza.persistence.entity.OrderEntity;
import com.dieguidev.pizza.service.CustomerService;
import com.dieguidev.pizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<CustomerEntity> findByPhone(@PathVariable String phone) {
        return ResponseEntity.ok(this.customerService.findByPhone(phone));
    }

    @GetMapping("/idCustomer/{idCustomer}")
    public  ResponseEntity<List<OrderEntity>> getCustomerOrder(@PathVariable String idCustomer) {
        return ResponseEntity.ok(this.orderService.getOrdersByCustomer(idCustomer));
    }
}
