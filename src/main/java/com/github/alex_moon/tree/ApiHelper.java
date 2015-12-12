package com.github.alex_moon.tree;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.User;
import com.github.alex_moon.tree.models.interfaces.IUserDao;

@Component
public class ApiHelper {
    private static RegexRequestMatcher apiUrlMatcher = new RegexRequestMatcher("/api/.*", null);

    // @todo is this thread-safe?
    private User sessionUser;

    @Autowired
    private IUserDao userDao;

    public static boolean isApiRequest(HttpServletRequest request) {
        return apiUrlMatcher.matches(request);
    }

    @Transactional
    public boolean isValidApiRequest(HttpServletRequest request) {
        String requestApiKey = request.getHeader("X-Api-Key");
        sessionUser = userDao.getByApiKey(requestApiKey);
        return sessionUser != null;
    }

    public User getSessionUser() {
        return sessionUser;
    }
}
