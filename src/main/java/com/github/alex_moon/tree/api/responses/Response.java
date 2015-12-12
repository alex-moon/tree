package com.github.alex_moon.tree.api.responses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

import com.github.alex_moon.tree.models.Model;

public class Response {
    Map<String, String> errors;
    Model result;
    String status;

    public Response(List<FieldError> errors) {
        this.errors = new HashMap<String, String>();
        for (FieldError error : errors) {
            this.errors.put(error.getField(), error.getDefaultMessage());
        }
        status = "error";
    }

    public Response(Model result) {
        this.result = result;
        status = "success";
    }
}
