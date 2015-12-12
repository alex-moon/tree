package com.github.alex_moon.tree.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Model {
    public static final int USER_TYPE_UNASSIGNED = 1;
    public static final int USER_TYPE_BRANCH_MANAGER = 2;
    public static final int USER_TYPE_REGIONAL_MANAGER = 3;
    public static final int USER_TYPE_NATIONAL_MANAGER = 4;
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

    public boolean isBranchManager() {
        return userTypeId == USER_TYPE_BRANCH_MANAGER;
    }
    public boolean isCustomer() {
        return userTypeId == USER_TYPE_CUSTOMER;
    }
}
