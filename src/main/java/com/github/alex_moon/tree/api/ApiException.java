package com.github.alex_moon.tree.api;

public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;
    public ApiException(String message) { super(message); }
    public ApiException(String message, Throwable cause) { super(message, cause); }
}