package com.github.alex_moon.tree.models.customers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class CustomerDAO implements ICustomerDAO {
    private SessionFactory sessionFactory;
    
    // @todo reflection-based property injection :)
    public CustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Transactional
    public List<Customer> list() {
        @SuppressWarnings("unchecked")
        List<Customer> listCustomer = (List<Customer>) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listCustomer;
    }
}
