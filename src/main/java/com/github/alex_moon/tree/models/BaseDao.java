package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;

public class BaseDao {
    protected SessionFactory sessionFactory;

    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
