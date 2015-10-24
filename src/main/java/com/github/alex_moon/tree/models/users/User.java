package com.github.alex_moon.tree.models.users;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
    private static final int USER_TYPE_UNASSIGNED = 1;
    private static final int USER_TYPE_BRANCH_MANAGER = 2;
    private static final int USER_TYPE_REGIONAL_MANAGER = 3;
    private static final int USER_TYPE_NATIONAL_MANAGER = 4;
    private static final int USER_TYPE_SUPERUSER = 5;
    private static final int USER_TYPE_CUSTOMER = 6;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="manager_user_id")
    private User manager;

    @OneToMany(fetch=FetchType.EAGER, mappedBy="manager")
    private Set<User> minions;

    @Column(name="user_type_id")
    private int userTypeId;

    public Set<User> getMinions() {
        return minions;
    }

    public void setMinions(Set<User> minions) {
        this.minions = minions;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String toString() {
        return String.format("%s <%s>", username, email);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isBranchManager() {
        return userTypeId == USER_TYPE_BRANCH_MANAGER;
    }
    public boolean isCustomer() {
        return userTypeId == USER_TYPE_CUSTOMER;
    }
}
