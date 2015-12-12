package com.github.alex_moon.tree;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TreeInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    ApiHelper apiHelper;

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws Exception {
        if (ApiHelper.isApiRequest(request)) {
            if (!apiHelper.isValidApiRequest(request)) {
                response.setStatus(403);
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final Object handler,
        final ModelAndView modelAndView
    ) throws IOException {
        if (modelAndView != null) {
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                modelAndView.getModelMap().addAttribute("user", principal.getName());
            }
        }
    }
}
