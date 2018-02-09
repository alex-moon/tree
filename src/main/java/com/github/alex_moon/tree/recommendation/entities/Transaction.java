package com.github.alex_moon.tree.recommendation.entities;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Transaction {
    @GraphId
    Long nodeId;

    String reference;
    // @todo BigDecimal spend;

    @Relationship(type="TRANSACTED", direction=Relationship.INCOMING)
    Set<Product> products = new HashSet<>();

    @Relationship(type="TRANSACTED", direction=Relationship.INCOMING)
    Set<Branch> branches = new HashSet<>();

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }
}
