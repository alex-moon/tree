package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;

import com.github.alex_moon.tree.models.interfaces.IBaseDao;

public class BaseDao implements IBaseDao {
    protected SessionFactory sessionFactory;

    public BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void persist(Object object) {
        sessionFactory.getCurrentSession().persist(object);
    }
}
