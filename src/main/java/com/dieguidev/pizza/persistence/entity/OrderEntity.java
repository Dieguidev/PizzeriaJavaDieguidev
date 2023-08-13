package com.dieguidev.pizza.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(name = "id_customer",nullable = false, length = 15)
    private String idCustomer;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date date;

    @Column(nullable = false, columnDefinition = "Decimal(6,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;

    //relaciones
    @ManyToOne(fetch = FetchType.LAZY)  //LAZY solo trae las relaciones cuando sean necesarias
    @JoinColumn(name = "id_customer", referencedColumnName = "id_customer",insertable = false,updatable = false)
    @JsonIgnore
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)  // trae todas las relaciones
    private List<OrderItemEntity> items;
}
