package com.example.coffee;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/coffees")
public class CoffeeController {
    private List<Coffee> coffees = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(CoffeeController.class);
    @Autowired
    CoffeeRepository coffeeRepository;
    public CoffeeController() {
        //coffees.addAll(
              //  List.of(
              //          new Coffee("Espresso"),
              //          new Coffee("Cappuccino"),
              //         new Coffee("Latte"),
              //          new Coffee("Ristretto"),
              //          new Coffee("Macciato")
              //  )
        //);
        coffeeRepository.saveAll(
                List.of(
                        new Coffee("Espresso"),
                        new Coffee("Cappuccino"),

                )
        )
    }

    //@GetMapping(path = "/coffees") // http://localhost:8080/coffees
    @GetMapping
    public Iterable<Coffee> getCoffees()
    // public List<Coffee> getCoffees()
    {
        return coffees;
    }
  // напишите метод который вернет конкретное кофе по его идентификатору
  // http://localhost:8080/coffees/aeb62ee3-3111-41ff-b720-a7225b684b75
  // getCoffeeById
  //@GetMapping("/coffees/{id}")
  @GetMapping("/{id}")
  public Optional<Coffee> getCoffeeById(
          @PathVariable(name = "id") String id
  )
  {
      for(Coffee c : coffees)
      {
          if(c.getId().equals(id))
              return Optional.of(c);
      }
      return Optional.empty();
  }

    // напишите метод котрый удалит кофе по его идентификатору
  // DELETE http://localhost:8080/coffees/aeb62ee3-3111-41ff-b720-a7225b684b75
  // до 20:30
  // public void deleteCoffee(String id)

 // @DeleteMapping("/coffees/{id}")
  @DeleteMapping
  public void deleteCoffee(
          @PathVariable String id){
    coffees.removeIf(coffee -> coffee.getId().equals(id));
  }

  //@PostMapping("/coffees")
  @PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee)
    {
      coffees.add(coffee);
      return coffee;
    }
/*
 // @PutMapping("/coffees/{id}") // меняет элемент на сервере
  @PutMapping("/{id}")
  public Coffee putCoffee(
          @PathVariable(name = "id") String id,
          @RequestBody Coffee coffee
  )
  {
    int coffeeIndex = -1;
    for(Coffee c : coffees)
    {
      if(c.getId().equals(id))
      {
        coffeeIndex = coffees.indexOf(c);
        coffees.set(coffeeIndex, coffee);
      }
    }
    return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
  }
*/
@PutMapping("/{id}")
public ResponseEntity<Coffee> putCoffee(
        @PathVariable(name = "id") String id,
        @RequestBody Coffee coffee
)
{
    //log.info("PUT " + coffee.getId() + coffee.getName());
    int coffeeIndex = -1;
    for(Coffee c : coffees)
    {
        if(c.getId().equals(id))
        {
            coffeeIndex = coffees.indexOf(c);
            coffees.set(coffeeIndex, coffee);
        }
    }
    return (coffeeIndex == -1) ?
            new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
            new ResponseEntity<>(coffee, HttpStatus.OK);
}
    // напишите метод который вернет кофе по его названию
    // если такого нет вернуть null
    // PATCH http://localhost:8080/coffees/Espresso
    @PatchMapping("/{name}")
    public Optional<Coffee> getCoffeeByName(
            @PathVariable String name
    )
    {
        return
                coffees.stream()
                        .filter(coffee -> coffee.getName().equals(name))
                        .findFirst();
    }

}

