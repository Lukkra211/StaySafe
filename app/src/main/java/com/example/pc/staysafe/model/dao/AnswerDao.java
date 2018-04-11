package com.example.pc.staysafe.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.pc.staysafe.model.entity.Answer;
import com.example.pc.staysafe.model.entity.Question;

/**
 * Object defining operations over the database
 */
@Dao
public interface AnswerDao {

    /**
     * Fetch articles
     * @return Array of all articles in the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAnswer(Answer... answer);

    @Query("SELECT * FROM answer WHERE question_id == :question_id")
    Answer[] getAnswers(int question_id);
}
