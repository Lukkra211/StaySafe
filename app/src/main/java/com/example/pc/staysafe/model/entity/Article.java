package com.example.pc.staysafe.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Contains metadata about article, text itself is in {@link Page}
 */
@Entity(tableName = "article")
public class Article {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    public int articleId;
    public int minutes;
    public int pages;
    public String title;
    public int difficulty;

    public Difficulty getDifficulty() {
        return Difficulty.fromInt(difficulty);
    }

    public enum Difficulty {
        ELEMENTARY, INTERMEDIATE, ADVANCED, UNKNOWN;

        protected static Difficulty fromInt(int difficulty) {
            switch (difficulty) {
                case 1:
                    return ELEMENTARY;
                case 2:
                    return INTERMEDIATE;
                case 3:
                    return ADVANCED;
                default:
                    return UNKNOWN;
            }
        }
    }
}
