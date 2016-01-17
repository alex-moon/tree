package com.github.alex_moon.tree.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.alex_moon.tree.models.Customer;
import com.github.alex_moon.tree.recommendation.entities.Branch;
import com.github.alex_moon.tree.recommendation.repositories.BranchRepository;

@Service
public class RecommendationService {
    @Autowired
    BranchRepository branchRepository;

    public Iterable<Branch> findBranchesForCustomer(Customer customer) {
        String postcodePrefix = customer.getPostcode().split(" ")[0];
        return branchRepository.findByPostcode(postcodePrefix);
    }
}
