package com.github.alex_moon.tree.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TreeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        String apiKey = request.getHeader("X-Api-Key");
        // @todo pull it from the DB might be nice
        if (apiKey == null || !apiKey.toString().equals("123")) {
            response.setStatus(403);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

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
