package com.github.alex_moon.tree.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class AuthController {
    private static Integer LOGIN_FAIL = 1;

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/login", params={"error"}, method=RequestMethod.GET)
    public ModelAndView login(@RequestParam(value="error") Integer error) {
        ModelAndView model = new ModelAndView("login");
        model.addObject("error", getErrorMessageForCode(error));
        return model;
    }

    @RequestMapping(value="/login-fail", method=RequestMethod.GET)
    public String loginFail() {
        return String.format("redirect:/login?error=%s", LOGIN_FAIL);
    }

    private String getErrorMessageForCode(int code) {
        if (code == 1) {
            return "You entered an incorrect username or password.";
        }
        return "There was a technical error.";
    }
}
