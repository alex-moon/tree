package com.github.alex_moon.tree.models;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.BaseDao;
import com.github.alex_moon.tree.models.Branch;
import com.github.alex_moon.tree.models.Customer;

public class SpendDao extends BaseDao implements ISpendDao { 
    public SpendDao(SessionFactory sessionFactory) { super(sessionFactory); }

    private List<Spend> getWhere(Criterion criterion) {
        @SuppressWarnings("unchecked")
        List<Spend> spend = (List<Spend>) sessionFactory.getCurrentSession()
                .createCriteria(Spend.class)
                .add(criterion)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.desc("spendDate"))
                .list(); 
        return spend;
    }

    public List<Spend> getForCustomer(Customer customer) {
        return getWhere(Restrictions.eq("customer", customer));
    }

    public List<Spend> getForBranch(Branch branch) {
        return getWhere(Restrictions.eq("branch", branch));
    }
}
