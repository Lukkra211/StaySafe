package com.example.pc.staysafe.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.pc.staysafe.model.entity.Article;
import com.example.pc.staysafe.model.entity.Question;

/**
 * Object defining operations over the database
 */
@Dao
public interface QuestionDao {
    /**
     * Return all questions of article with given id
     * @param article_id id of the article
     * @return Array of pages
     */
    @Query("SELECT * FROM questionsTable WHERE article_id == :article_id")
    Question[] getQuestions(int article_id);
}
