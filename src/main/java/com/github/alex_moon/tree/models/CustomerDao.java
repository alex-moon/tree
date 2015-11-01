package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.BaseDao;
import com.github.alex_moon.tree.models.User;

public class CustomerDao extends BaseDao<Customer> implements ICustomerDao {
    public CustomerDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        model = Customer.class;
    }

    public Customer getForUser(User user) {
        Customer customer = (Customer) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .add(Restrictions.eq("user", user))
                .setMaxResults(1)
                .uniqueResult();
 
        return customer;
    }
}
