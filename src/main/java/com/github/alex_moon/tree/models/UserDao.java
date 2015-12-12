package com.github.alex_moon.tree.models;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.github.alex_moon.tree.models.interfaces.IUserDao;

public class UserDao extends BaseDao implements IUserDao {
    public UserDao(SessionFactory sessionFactory) { super(sessionFactory); }

    public List<User> list() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
 
        return listUser;
    }

    public User getByUsername(String username) {
        User user = (User) sessionFactory.getCurrentSession()
            .createCriteria(User.class)
            .add(Restrictions.eq("username", username))
            .uniqueResult();
        return user;
    }

    public User getByApiKey(String apiKey) {
        User user = (User) sessionFactory.getCurrentSession()
            .createCriteria(User.class)
            .add(Restrictions.eq("apiKey", apiKey))
            .uniqueResult();
        return user;
    }
}
