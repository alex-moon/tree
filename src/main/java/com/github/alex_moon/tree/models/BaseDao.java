package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;

import com.github.alex_moon.tree.api.requests.CreateRequest;

public class BaseDao<T> implements IBaseDao<T> {
    protected SessionFactory sessionFactory;
    protected Class<T> model;

    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public T create(CreateRequest request) {
        T object;
        try {
            object = model.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        sessionFactory.getCurrentSession().persist(object);
        return object;
    }
}
