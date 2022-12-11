package com.example.coffee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@RestController
@Controller
@RequestMapping("/coffees")
public class CoffeeController {
    //private List<Coffee> coffees = new ArrayList<>();

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
       /* coffeeRepository.saveAll(
                List.of(
                        new Coffee("Espresso"),
                        new Coffee("Cappuccino"),
                        new Coffee("Latte"),
                        new Coffee("Ristretto"),
                        new Coffee("Macciato")
                )
        );*/
    }

    //@GetMapping(path = "/coffees") // http://localhost:8080/coffees
    //@GetMapping
    //public Iterable<Coffee> getCoffees()
    // public List<Coffee> getCoffees()
    // {
    //     // return coffees;
    //     return coffeeRepository.findAll();
    //}

    @GetMapping
    public String getAllCoffees(Model model)
    {
        // Model позволяет биндить в html-template файл
        // экземпляры объектов которые потом будут
        // доступны шаблонизатору
        Iterable<Coffee> all  = coffeeRepository.findAll();
        model.addAttribute("coffees", all);
        return "list";
    }


    // напишите метод который вернет конкретное кофе по его идентификатору
  // http://localhost:8080/coffees/aeb62ee3-3111-41ff-b720-a7225b684b75
  // getCoffeeById
  //@GetMapping("/coffees/{id}")
  @GetMapping("/{id}")
  public Optional<Coffee> getCoffeeById(
          @PathVariable(name = "id") String id
  ) {
//        return
//                coffees.stream().filter(coffee -> coffee.getId().equals(id))
//                        .findFirst();

//        for(Coffee c : coffees)
//        {
//            if(c.getId().equals(id))
//                return Optional.of(c);
//        }
//        return Optional.empty();

      return coffeeRepository.findById(id);
  }

    @GetMapping("/add")
    public String addNewCoffee(Coffee coffee)
    {
      return "add-coffee";
    }


    // напишите метод котрый удалит кофе по его идентификатору
  // DELETE http://localhost:8080/coffees/aeb62ee3-3111-41ff-b720-a7225b684b75
  // до 20:30
  // public void deleteCoffee(String id)

 // @DeleteMapping("/coffees/{id}")
/* @DeleteMapping("/{id}")
 public void deleteCoffee(
         @PathVariable(name = "id") String id
 ) {
     log.info("delete: " + id);
     // coffees.removeIf(coffee -> coffee.getId().equals(id));
     coffeeRepository.deleteById(id);
 }
*/
 @PostMapping
 public String addCoffee(Coffee coffee, Model model)
 {
     coffeeRepository.save(coffee);
     return "redirect:/coffees";
 }

    @GetMapping("/edit/{id}")
    public String editACoffee(
            @PathVariable (name = "id") String id,
            Model model
    )
    {
        Coffee coffee = coffeeRepository.findById(id).get();
        model.addAttribute("coffee", coffee);
        return "update-coffee";
    }

    @PostMapping("/update/{id}")
    public String updateCoffee(
            @PathVariable (name = "id") String id,
            Coffee coffee
    )
    {
        log.info("updateCoffee " + coffee);
        coffeeRepository.save(coffee);
        return "redirect:/coffees";
    }



    //@PostMapping("/coffees") // СЃРѕР·РґР°РµС‚ РЅР° СЃРµСЂРІРµСЂРµ РЅРѕРІС‹Р№ СЌР»РµРјРµРЅС‚
    // {"id": "23123", "name"="My coffee" }
    /*@PostMapping
    public Coffee postCoffee(@RequestBody Coffee coffee) {
        if (coffee != null
                && coffee.getId() != null
                && !coffee.getId().isEmpty()
                && coffee.getName() != null
                && !coffee.getName().isEmpty()
        )
            coffeeRepository.save(coffee);
        //coffees.add(coffee);
        return coffee;
    }  */

 // GET http://localhost:8080/coffees/delete/aeb62ee3-3111-41ff-b720-a7225b684b75
 @GetMapping("/delete/{id}")
 public String deleteCoffeeById(
         @PathVariable(name = "id") String id
 )
 {
     log.info("deleteCoffeeById id: " + id);
     coffeeRepository.deleteById(id);
     return "redirect:/coffees";
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
   /* @PutMapping("/{id}")
    public ResponseEntity<Coffee> putCoffee(
            @PathVariable(name = "id") String id,
            @RequestBody Coffee coffee
    ) {
        log.info("PUT " + coffee.getId() + "|" + coffee.getName());
        /*
        int coffeeIndex = -1;
        for (Coffee c : coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);



        if(coffeeRepository.existsById(id))
        {
            coffeeRepository.save(coffee);
            return new ResponseEntity<>(coffee, HttpStatus.OK);
        }
        else {
            coffeeRepository.save(coffee);
            return new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED);
        }
    }
*/
    // напишите метод который вернет кофе по его названию
    // если такого нет вернуть null
    // PATCH http://localhost:8080/coffees/Espresso
    @PatchMapping("/{name}")
    public List<Coffee> getCoffeeByName(
            @PathVariable String name
    ) {
        return
                coffeeRepository.findByNameContaining(name);
    }

    // http://localhost:8080/coffees/search?name=tt
    @GetMapping("/search")
    public String searchCoffee (
            // сюда приходит поисковый запрос в виде строчки
            @RequestParam String name,
            Model model // модель нужна чтобы пробросить выбранные кофе в шаблон
    )
    {
        // извлекаем по запросу из репо все кофе с нужным именем
        List<Coffee> result = coffeeRepository.findByNameContaining(name);
        // биндим эти кофе в модель
        model.addAttribute("coffees", result);
        return "list";   // возвращаем шаблон в котором по ключу будут доступны нужные кофе
    }


}

