package com.github.alex_moon.tree.models;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

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

    public List<Branch> getForAreaManager(User user) {
        @SuppressWarnings("unchecked")
        List<Branch> branches = (List<Branch>) sessionFactory.getCurrentSession()
            .createCriteria(Branch.class, "branch")
            .setFetchMode("branch.user", FetchMode.JOIN)
            .createAlias("branch.user", "user", JoinType.INNER_JOIN)
            .setFetchMode("user.manager", FetchMode.JOIN)
            .add(Restrictions.eq("user.manager", user))
            .list();
        return branches;
    }

    public List<Branch> getForRegionalManager(User user) {
        @SuppressWarnings("unchecked")
        List<Branch> branches = (List<Branch>) sessionFactory.getCurrentSession()
            .createCriteria(Branch.class, "branch")
            .setFetchMode("branch.user", FetchMode.JOIN)
            .createAlias("branch.user", "user", JoinType.INNER_JOIN)
            .setFetchMode("user.manager", FetchMode.JOIN)
            .createAlias("user.manager", "manager", JoinType.INNER_JOIN)
            .setFetchMode("manager.manager", FetchMode.JOIN)
            .add(Restrictions.eq("manager.manager", user))
            .list();
        return branches;
    }
}
