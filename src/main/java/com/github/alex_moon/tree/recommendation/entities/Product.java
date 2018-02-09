package com.github.alex_moon.tree.recommendation.entities;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Product {
    @GraphId
    public Long nodeId;

    public String name;
    public String barcode;
    // @todo RRP? Could be handy I guess... 

    @Relationship(type="TRANSACTED", direction=Relationship.OUTGOING)
    Set<Transaction> transactions = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
