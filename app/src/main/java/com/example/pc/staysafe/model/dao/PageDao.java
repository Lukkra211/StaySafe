package com.example.pc.staysafe.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.pc.staysafe.model.entity.Page;

/**
 * Object defining operations over the database
 */
@Dao
public interface PageDao {
    /**
     * Return all pages of article with given id
     * @param article_id id of the article
     * @return Array of pages
     */
    @Query("SELECT * FROM page WHERE article_id == :article_id")
    Page[] getArticlePages(int article_id);
}
