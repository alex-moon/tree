package com.github.alex_moon.tree.recommendation.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.github.alex_moon.tree.recommendation.entities.Branch;

public interface BranchRepository extends GraphRepository<Branch> {
    @Query(
          "MATCH (branch:Branch)-[:STOCKS]->(products:Product) "
        + "WHERE branch.postcode =~ ({0} + '.*') "
        + "RETURN branch, products"
    )
    Iterable<Branch> findByPostcode(String postcode);
}
