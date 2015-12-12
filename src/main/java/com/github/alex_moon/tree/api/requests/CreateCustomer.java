package com.github.alex_moon.tree.api.requests;

import javax.validation.constraints.NotNull;

public class CreateCustomer extends CreateUser {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
