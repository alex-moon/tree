package com.github.alex_moon.tree.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Model {
    public static final int USER_TYPE_UNASSIGNED = 1;
    public static final int USER_TYPE_BRANCH_MANAGER = 2;
    public static final int USER_TYPE_AREA_MANAGER = 3;
    public static final int USER_TYPE_REGIONAL_MANAGER = 4;
    public static final int USER_TYPE_SUPERUSER = 5;
    public static final int USER_TYPE_CUSTOMER = 6;

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

    @Column(name="user_type_id")
    private int userTypeId;

    @Column(name="api_key")
    private String apiKey;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="manager_user_id")
    private User manager;

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

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public int getUserTypeId() {
        return userTypeId;
    }
    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public void setUserTypeBranchManager() {
        this.userTypeId = USER_TYPE_BRANCH_MANAGER;
    }
    public void setUserTypeCustomer() {
        this.userTypeId = USER_TYPE_CUSTOMER;
    }

    public boolean isSuperuser() {
        return userTypeId == USER_TYPE_SUPERUSER;
    }
    public boolean isRegionalManager() {
        return userTypeId == USER_TYPE_REGIONAL_MANAGER;
    }
    public boolean isAreaManager() {
        return userTypeId == USER_TYPE_AREA_MANAGER;
    }
    public boolean isBranchManager() {
        return userTypeId == USER_TYPE_BRANCH_MANAGER;
    }
    public boolean isCustomer() {
        return userTypeId == USER_TYPE_CUSTOMER;
    }
    public String getApiKey() {
        return apiKey;
    }
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
