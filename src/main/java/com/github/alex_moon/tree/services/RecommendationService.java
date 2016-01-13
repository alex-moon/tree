package com.github.alex_moon.tree.services;

import org.springframework.stereotype.Service;

import com.github.alex_moon.tree.recommendation.Recommendation;

@Service
public class RecommendationService {
    public RecommendationService() {
    }

    public Recommendation getByUsername(String username) {
        Recommendation result = new Recommendation();
        result.setRecommendation("lol shit here");
        return result;
    }
}
