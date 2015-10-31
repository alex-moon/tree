package com.github.alex_moon.tree.models.spend;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.BaseDao;
import com.github.alex_moon.tree.models.branches.Branch;
import com.github.alex_moon.tree.models.customers.Customer;
import com.github.alex_moon.tree.models.users.User;

public class SpendDao extends BaseDao implements ISpendDao { 
    public SpendDao(SessionFactory sessionFactory) { super(sessionFactory); }

    public List<Spend> getForCustomer(Customer customer) {
        @SuppressWarnings("unchecked")
        List<Spend> spend = (List<Spend>) sessionFactory.getCurrentSession()
                .createCriteria(Spend.class)
                .add(Restrictions.eq("customer", customer))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list(); 
        return spend;
    }

    public List<Spend> getForBranch(Branch branch) {
        @SuppressWarnings("unchecked")
        List<Spend> spend = (List<Spend>) sessionFactory.getCurrentSession()
                .createCriteria(Spend.class)
                .add(Restrictions.eq("branch", branch))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list(); 
        return spend;
    }
}
