package com.example.pc.staysafe.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Answer to one {@link Question}
 */
@Entity(tableName = "answer",
        foreignKeys = @ForeignKey(entity = Question.class,
                                  parentColumns = "question_id",
                                  childColumns = "answer_id"))
public class Answer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "answer_id")
    public int answerId;

    @ColumnInfo(name = "question_id")
    public int questionId;

    public String answer;
    public boolean correct;
}
