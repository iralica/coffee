package com.example.coffee;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;
@Entity
@Table // esly hotim nazvati tablitu po drugomu
public class Coffee {
    private final String id;
    private String name;
    public Coffee()
    {
        this("name");
    }
    public Coffee(String id, String name){
        this.id = id;
        this.name = name;
    }
    public Coffee(String name){
        this(UUID.randomUUID().toString(),
                name
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
