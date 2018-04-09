package com.example.pc.staysafe.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.pc.staysafe.R;

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

    public int getColor(Context context) {
        switch (getDifficulty()) {
            case ELEMENTARY:
                return ContextCompat.getColor(context, R.color.levelElementary);

            case INTERMEDIATE:
                return ContextCompat.getColor(context, R.color.levelIntermediate);

            case ADVANCED:
                return ContextCompat.getColor(context, R.color.levelAdvanced);

            default:
                return ContextCompat.getColor(context, R.color.levelAdvanced);
        }
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
