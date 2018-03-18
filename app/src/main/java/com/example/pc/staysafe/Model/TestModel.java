package com.example.pc.staysafe.Model;

import com.example.pc.staysafe.Model.Objects.Article;

import java.util.ArrayList;

/**
 * Fake model for developing DangerActivity
 */
public class TestModel {

    /**
     * Simulate getting articles from the database
     * @param dangerType type of danger the user selected
     * @return ArrayList of articles in database with given type
     */
    public ArrayList<Article> getArticles(DangerType dangerType) {
        ArrayList<Article> articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // Create subarticles
            ArrayList<String> subarticles = new ArrayList<>();
            for (int n = 0; n < 4; n++) {
                subarticles.add(String.format("Subarticle %d of Article %d", n, i));
            }

            String articleTitle = String.format("Article %d ", i);
            articleTitle += (dangerType == DangerType.REAL) ? "(Real danger)" : "(Virtual danger)";
            // Append new Article
            articles.add(new Article(articleTitle, i, subarticles));
        }
        return articles;
    }

    /**
     * Represents type of danger
     */
    public enum DangerType {
        VIRTUAL, REAL;
    }
}
