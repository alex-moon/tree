package com.github.alex_moon.tree.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.alex_moon.tree.api.requests.CreateBranch;
import com.github.alex_moon.tree.api.requests.CreateCustomer;
import com.github.alex_moon.tree.api.responses.Response;
import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.Model;
import com.github.alex_moon.tree.services.BranchService;
import com.github.alex_moon.tree.services.CustomerService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private BranchService branchService;
    
    protected ResponseEntity<Response> errorResponse(BindingResult bindingResult) {
        Response response = new Response(bindingResult.getFieldErrors());
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
    
    protected ResponseEntity<Response> successResponse(Model entityResult) {
        return new ResponseEntity<Response>(new Response(entityResult), HttpStatus.OK);
    }

    @RequestMapping(value="/customers", method=RequestMethod.POST)
    public ResponseEntity<Response> createCustomer(@RequestBody @Valid CreateCustomer request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return errorResponse(bindingResult);
        }
        Customer customer = customerService.createCustomer(request);
        return successResponse(customer);
    }

    @RequestMapping(value="/branches", method=RequestMethod.POST)
    public ResponseEntity<Response> createBranch(@RequestBody @Valid CreateBranch request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return errorResponse(bindingResult);
        }
        Branch branch = branchService.createBranch(request);
        return successResponse(branch);
    }
}
