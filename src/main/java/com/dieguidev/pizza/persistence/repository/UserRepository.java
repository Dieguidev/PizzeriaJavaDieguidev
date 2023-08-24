package com.dieguidev.pizza.persistence.repository;

import com.dieguidev.pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String>{

}
