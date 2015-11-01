package com.github.alex_moon.tree.api.requests;

public class CreateBranch extends CreateUser {
    private String name;
    private String postcode;
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
