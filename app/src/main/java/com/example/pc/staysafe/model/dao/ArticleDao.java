package com.example.pc.staysafe.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.pc.staysafe.model.entity.Article;

/**
 * Object defining operations over the database
 */
@Dao
public interface ArticleDao {

    /**
     * Fetch articles
     * @return Array of all articles in the database
     */
    @Query("SELECT * FROM article")
    Article[] fetchArticles();
}
