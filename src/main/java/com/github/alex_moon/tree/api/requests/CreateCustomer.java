package com.github.alex_moon.tree.api.requests;

public class CreateCustomer extends CreateUser {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
