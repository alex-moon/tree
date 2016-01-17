package com.github.alex_moon.tree.recommendation.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Branch {
    @GraphId
    Long nodeId;

    String id;
    String name;
    String postcode;

    @Relationship(type="STOCKS", direction=Relationship.OUTGOING)
    Set<Product> products = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Relationship(type="STOCKS", direction=Relationship.OUTGOING)
    public Collection<Product> getProducts() {
        return IteratorUtil.asCollection(products);
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
