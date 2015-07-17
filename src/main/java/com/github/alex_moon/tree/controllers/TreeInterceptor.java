package com.github.alex_moon.tree.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TreeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler,
        final ModelAndView modelAndView
    ) {
        if (modelAndView != null) {
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                modelAndView.getModelMap().addAttribute("user", principal.getName());
            }
        }
    }
}
