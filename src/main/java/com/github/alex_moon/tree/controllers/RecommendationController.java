package com.github.alex_moon.tree.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.services.RecommendationService;

@Controller
public class RecommendationController {
    @Autowired
    private RecommendationService service;
    
    @RequestMapping(value="/recommendation/")
    public ModelAndView spend(Principal principal) {
        String username = principal.getName();
        ModelAndView model = new ModelAndView("recommend");
        model.addObject("recommendation", service.getByUsername(username));
        return model;
    }
}
