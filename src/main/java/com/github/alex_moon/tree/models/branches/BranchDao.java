package com.github.alex_moon.tree.models.branches;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.BaseDao;
import com.github.alex_moon.tree.models.users.User;

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
