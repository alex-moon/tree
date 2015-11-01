package com.github.alex_moon.tree.models;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.interfaces.IBranchDao;

public class BranchDao extends BaseDao implements IBranchDao { 
    public BranchDao(SessionFactory sessionFactory) { super(sessionFactory); }

    public Branch getForUser(User user) {
        Branch branch = (Branch) sessionFactory.getCurrentSession()
                .createCriteria(Branch.class)
                .add(Restrictions.eq("user", user))
                .setMaxResults(1)
                .uniqueResult();
        return branch;
    }
}
