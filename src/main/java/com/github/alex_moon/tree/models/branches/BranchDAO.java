package com.github.alex_moon.tree.models.branches;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.github.alex_moon.tree.models.BaseDAO;
import com.github.alex_moon.tree.models.users.User;

public class BranchDAO extends BaseDAO implements IBranchDAO { 
    public BranchDAO(SessionFactory sessionFactory) { super(sessionFactory); }

    @Transactional
    public Branch getForUser(User user) {
        Branch branch = (Branch) sessionFactory.getCurrentSession()
                .createCriteria(Branch.class)
                .add(Restrictions.eq("user", user))
                .setMaxResults(1)
                .uniqueResult();
 
        return branch;
    }
}
