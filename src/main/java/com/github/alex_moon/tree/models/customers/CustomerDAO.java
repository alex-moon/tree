package com.github.alex_moon.tree.models.customers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.users.User;

public class CustomerDAO implements ICustomerDAO {
    private SessionFactory sessionFactory;

    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Transactional
    public List<Customer> allocatedTo(User manager) {
        @SuppressWarnings("unchecked")
        List<Customer> customers = (List<Customer>) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .add(Restrictions.eq("manager", manager))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return customers;
    }
    
    public List<Customer> allUnder(User manager) {
        // @todo stub
        return allocatedTo(manager);
    }
}
