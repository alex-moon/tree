package com.github.alex_moon.tree.models.interfaces;

import com.github.alex_moon.tree.api.requests.CreateRequest;

public interface IBaseDao<T> {
    public T create(CreateRequest request);
}
