package com.github.alex_moon.tree.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.services.CustomerService;
import com.github.alex_moon.tree.services.RecommendationService;

@Controller
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;
    
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping(value="/recommendation/")
    public ModelAndView spend(Principal principal) {
        String username = principal.getName();
        Customer customer = customerService.getByUsername(username);
        ModelAndView model = new ModelAndView("recommendation");
        model.addObject("branches", recommendationService.findBranchesForCustomer(customer));
        return model;
    }
}
