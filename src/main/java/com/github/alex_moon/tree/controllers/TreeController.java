package com.github.alex_moon.tree.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.services.SpendService;
import com.github.alex_moon.tree.services.UserService;

@Controller
public class TreeController {
    @Autowired
    private SpendService spendService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/")
    public String home() { return "home"; }

    @RequestMapping(value="/spend/")
    public ModelAndView spend(Principal principal) {
        User user = userService.getByUsername(principal.getName());
        String template = getSpendTemplateForUser(user);
        ModelAndView model = new ModelAndView(template);
        model.addObject("spend", spendService.getForUser(user));
        return model;
    }

    private String getSpendTemplateForUser(User user) {
        if (user.isBranchManager()) {
            return "branch-spend";
        } else if (user.isCustomer()) {
            return "customer-spend";
        }
        return "spend";
    }
}
