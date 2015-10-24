package com.github.alex_moon.tree.models.customers;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.BaseDAO;
import com.github.alex_moon.tree.models.users.User;

public class CustomerDAO extends BaseDAO implements ICustomerDAO {
    public CustomerDAO(SessionFactory sessionFactory) { super(sessionFactory); }

    @Transactional
    public List<Customer> getAllAllocatedTo(User manager) {
        @SuppressWarnings("unchecked")
        List<Customer> customers = (List<Customer>) sessionFactory.getCurrentSession()
                .createCriteria(Customer.class)
                .add(Restrictions.eq("manager", manager))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return customers;
    }

    @Transactional
    public List<Customer> getAllUnder(User manager) {
        List<Customer> customers = getAllAllocatedTo(manager);
        for (User minion : manager.getMinions()) {
            for (Customer customer : getAllUnder(minion)) {
                customers.add(customer);
            }
        }
        return customers;
    }
}
