package com.github.alex_moon.tree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TreeController {
    @RequestMapping(value="/")
    public String home() { return "home"; }
}
