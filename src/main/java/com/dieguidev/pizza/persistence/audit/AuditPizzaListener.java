package com.dieguidev.pizza.persistence.audit;

import com.dieguidev.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.*;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity) {
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(entity);
    }




    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity) {
        System.out.println("POST PERSIST OR UPDATE");
        System.out.println("OLD VALUE: " + this.currentValue);
        System.out.println("NEW VALUE: " + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity) {
        System.out.println(entity.toString());
    }
}
