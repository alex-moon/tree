package com.github.alex_moon.tree.models;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.interfaces.ITransactionDao;

public class TransactionDao extends BaseDao implements ITransactionDao { 
    public TransactionDao(SessionFactory sessionFactory) { super(sessionFactory); }

    private List<Transaction> getWhere(Criterion criterion) {
        @SuppressWarnings("unchecked")
        List<Transaction> spend = (List<Transaction>) sessionFactory.getCurrentSession()
            .createCriteria(Transaction.class)
            .add(criterion)
            .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
            .addOrder(Order.desc("createdDate"))
            .list(); 
        return spend;
    }

    public List<Transaction> getForCustomer(Customer customer) {
        return getWhere(Restrictions.eq("customer", customer));
    }

    public List<Transaction> getForBranch(Branch branch) {
        return getWhere(Restrictions.eq("branch", branch));
    }

    public List<Transaction> getForBranches(List<Branch> branches) {
        return getWhere(Restrictions.in("branch", branches));
    }
}
