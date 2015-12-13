package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.interfaces.ICustomerDao;

public class CustomerDao extends BaseDao implements ICustomerDao {
    public CustomerDao(SessionFactory sessionFactory) { super(sessionFactory); }

    public Customer getForUser(User user) {
        Customer customer = (Customer) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .add(Restrictions.eq("user", user))
                .setMaxResults(1)
                .uniqueResult();
        return customer;
    }

    public Customer getForBarcode(String customerBarcode) {
        Customer customer = (Customer) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .add(Restrictions.eq("barcode", customerBarcode))
                .setMaxResults(1)
                .uniqueResult();
        return customer;
    }
}
