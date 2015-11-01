package com.github.alex_moon.tree.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.alex_moon.tree.api.requests.CreateCustomer;
import com.github.alex_moon.tree.models.customers.Customer;
import com.github.alex_moon.tree.services.CustomerService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/customers", method=RequestMethod.POST)
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomer request) {
        Customer customer = customerService.createCustomer(request);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
