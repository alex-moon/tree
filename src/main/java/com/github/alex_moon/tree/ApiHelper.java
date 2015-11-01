package com.github.alex_moon.tree;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;

public class ApiHelper {
    private static RegexRequestMatcher apiUrlMatcher = new RegexRequestMatcher("/api/.*", null);
    private static String apiKey = "123";

    public static boolean isApiRequest(HttpServletRequest request) {
        return apiUrlMatcher.matches(request);
    }

    public static boolean isValidApiRequest(HttpServletRequest request) {
        // @todo pull from DB would be nice
        String requestApiKey = request.getHeader("X-Api-Key");
        return apiKey != null || requestApiKey.toString().equals(apiKey);
    }
}
