package com.github.alex_moon.tree.models.users;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

public class UserDao implements IUserDao {
    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Transactional
    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
 
        return listUser;
    }
    
    @Transactional
    public User getByUsername(String username) {
        User user = (User) sessionFactory.getCurrentSession()
            .createCriteria(User.class)
            .add(Restrictions.eq("username", username))
            .uniqueResult();
        return user;
    }
}