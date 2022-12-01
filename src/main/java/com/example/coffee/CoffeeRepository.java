package com.example.coffee;

import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {
   // budet cerez repository sohraneatsea v tablite
    // String - tip cliucha
}
