package com.github.alex_moon.tree.recommendation.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.github.alex_moon.tree.recommendation.entities.Branch;

public interface BranchRepository extends GraphRepository<Branch> {
    @Query(
          "MATCH (branch:Branch) "
        + "WHERE branch.postcode =~ ({0} + '.*') "
        + "RETURN branch, products"
    )
    Iterable<Branch> findByPostcode(String postcode);

    // @todo [:TRANSACTED] would be fine - the relationships aren't special
    // @todo figure out how to make node type unique for relationship
    @Query(
        "MATCH (c:Customer)-[:TRANSACTED]->(notT)<-[:TRANSACTED]-(p:Product), "
      + "      (p)-[tp:TRANSACTED]->(t)<-[tb:TRANSACTED]-(b:Branch) "
      + "WHERE c.id = {0} "
      + "RETURN b, tb, t, tp, p"
    )
    Iterable<Branch> recommendForCustomer(int customerId);
}
