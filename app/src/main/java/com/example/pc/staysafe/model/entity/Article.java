package com.example.pc.staysafe.Model.entity;

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
    public int difficulty;
    public String title;
}
