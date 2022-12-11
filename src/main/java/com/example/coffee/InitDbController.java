package com.example.coffee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class InitDbController implements CommandLineRunner {
    // CommandLineRunner запускается сразу после старта spring приложения
    // используется для программной инициализации чего-либо

    private static final Logger log = LoggerFactory.getLogger(InitDbController.class);

    private CoffeeRepository coffeeRepository;

    // инжекция зависимости через конструктор упрощает отладку приложения
    // так как все проблемы проявляются раньше
    @Autowired
    public InitDbController(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Saving initial coffees");

        coffeeRepository.saveAll(
                Set.of(
                        new Coffee("Ristretto"),
                        new Coffee("Espresso"),
                        new Coffee("Cappuccino"),
                        new Coffee("Latte")
                )
        );
    }
}
