package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;

public class BaseDAO {
    protected SessionFactory sessionFactory;

    public BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
