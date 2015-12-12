package com.github.alex_moon.tree.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.alex_moon.tree.ApiHelper;
import com.github.alex_moon.tree.api.requests.CreateBranch;
import com.github.alex_moon.tree.api.requests.CreateCustomer;
import com.github.alex_moon.tree.api.requests.CreateSpend;
import com.github.alex_moon.tree.api.responses.Response;
import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.models.Model;
import com.github.alex_moon.tree.models.Spend;
import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.services.BranchService;
import com.github.alex_moon.tree.services.CustomerService;
import com.github.alex_moon.tree.services.SpendService;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiHelper apiHelper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private SpendService spendService;

    protected ResponseEntity<Response> errorResponse(BindingResult bindingResult) {
        Response response = new Response(bindingResult.getFieldErrors());
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    protected ResponseEntity<Response> forbiddenResponse() {
        Map<String, String> errors = new HashMap<String, String>();
        errors.put("", "You are not authorised to make this request.");
        return new ResponseEntity<Response>(new Response(errors), HttpStatus.FORBIDDEN);
    }

    protected ResponseEntity<Response> successResponse(Model entityResult) {
        return new ResponseEntity<Response>(new Response(entityResult), HttpStatus.OK);
    }
    
    protected User getSessionUser() {
        return apiHelper.getSessionUser();
    }

    @RequestMapping(value="/customers", method=RequestMethod.POST)
    public ResponseEntity<Response> createCustomer(@RequestBody @Valid CreateCustomer request, BindingResult bindingResult) {
        if (!getSessionUser().isSuperuser()) {
            return forbiddenResponse();
        }
        if (bindingResult.hasErrors()) {
            return errorResponse(bindingResult);
        }
        Customer customer = customerService.createCustomer(request);
        return successResponse(customer);
    }

    @RequestMapping(value="/branches", method=RequestMethod.POST)
    public ResponseEntity<Response> createBranch(@RequestBody @Valid CreateBranch request, BindingResult bindingResult) {
        if (!getSessionUser().isSuperuser()) {
            return forbiddenResponse();
        }
        if (bindingResult.hasErrors()) {
            return errorResponse(bindingResult);
        }
        Branch branch = branchService.createBranch(request);
        return successResponse(branch);
    }

    @RequestMapping(value="/spend", method=RequestMethod.POST)
    public ResponseEntity<Response> createSpend(@RequestBody @Valid CreateSpend request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return errorResponse(bindingResult);
        }
        Spend spend = spendService.createSpend(request);
        return successResponse(spend);
    }
}
