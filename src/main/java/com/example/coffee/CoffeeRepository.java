package com.example.coffee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, String> {
    // Coffee - класс который будет через repo сохраняться в таблице базы данных
    // String - тип ключа этого класса
    List<Coffee> findByNameContaining(String name);
}
