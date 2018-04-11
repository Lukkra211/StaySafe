package com.example.pc.staysafe.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Question on one {@link Article}
 */
@Entity(tableName = "question", foreignKeys = @ForeignKey(
        entity = Article.class,
        parentColumns = "article_id",
        childColumns = "question_id"))
public class Question {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "question_id")
    public int questionId;

    @ColumnInfo(name = "article_id")
    public int articleId;

    public int type;
    public String question;

    public Type getType() {
        return Type.fromInt(type);
    }

    public enum Type {
        SINGLE_ANSWEAR, MULTIPLE_ANSWEAR, NUMBER, TRUE_FALSE, UNKNOWN;

        public static Type fromInt(int type) {
            switch (type) {
                case 1:
                    return SINGLE_ANSWEAR;

                case 2:
                    return MULTIPLE_ANSWEAR;

                case 3:
                    return NUMBER;

                case 4:
                    return TRUE_FALSE;

                default:
                    return UNKNOWN;
            }
        }
    }
}