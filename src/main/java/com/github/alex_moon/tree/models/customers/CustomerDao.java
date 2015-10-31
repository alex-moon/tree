package com.github.alex_moon.tree.models.customers;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.BaseDao;
import com.github.alex_moon.tree.models.users.User;

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
}
