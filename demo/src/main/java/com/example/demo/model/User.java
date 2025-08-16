package com.example.demo.model;

import java.util.UUID;


public class User {
    private UUID id;
    private String name;
    private String surname;

    public String getFullName() {
        return name + " " + surname;
    }
}
